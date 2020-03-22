package com.xxnjdg.heimacms.cmsservice.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.xxnjdg.heimacms.cmsservice.dao.CmsPageDao;
import com.xxnjdg.heimacms.cmsservice.producer.RabbitStaticHtmlSender;
import com.xxnjdg.heimacms.cmsservice.service.CmsHtmlRetryMessageService;
import com.xxnjdg.heimacms.cmsservice.service.CmsPageService;
import com.xxnjdg.heimacms.cmsservice.service.CmsTemplateService;
import com.xxnjdg.heimacms.common.exception.CustomException;
import com.xxnjdg.heimacms.common.model.response.CommonCode;
import com.xxnjdg.heimacms.common.model.response.ResponseResult;
import com.xxnjdg.heimacms.servicemodel.cmsservice.CmsHtmlRetryMessage;
import com.xxnjdg.heimacms.servicemodel.cmsservice.CmsPage;
import com.xxnjdg.heimacms.servicemodel.cmsservice.CmsTemplate;
import com.xxnjdg.heimacms.servicemodel.cmsservice.response.ResponseCmsPageList;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsCriteria;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
/**
 * @author djr
 * @version 1.0
 * @date 2020/3/9 11:47
 */
@Service
public class CmsPageServiceImpl implements CmsPageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CmsPageServiceImpl.class);

    @Autowired
    private CmsPageDao cmsPageDao;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Autowired
    private CmsTemplateService cmsTemplateService;

    @Autowired
    private CmsHtmlRetryMessageService cmsHtmlRetryMessageService;

    @Autowired
    private RabbitStaticHtmlSender rabbitStaticHtmlSender;

    @Value("${project.rabbitmq.exchange}")
    private String exchange;

    @Value("${project.rabbitmq.routingkey}")
    private String routingKey;

    @Override
    public List<CmsPage> getAll() {
        return cmsPageDao.findAll();
    }

    /**
     * 1 分页查询CmsPage 集合下的数据
     * 2 根据站点Id、模板Id、页面别名查询页面信息
     * 3 支持分页及自定义条件查询方式
     *
     * @param siteId     站点Id
     * @param templateId 模板Id
     * @param pageAliase 页面别名
     * @param page       页数
     * @param size       行数
     * @return ResponeCmsPage vo
     */
    @Override
    public ResponseCmsPageList queryCmsPageList(String siteId, String templateId, String pageAliase, int page, int size) {

        CmsPage cmsPage = new CmsPage();
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("pageAliase", ExampleMatcher.GenericPropertyMatchers.contains());

        if (StringUtils.isNoneBlank(siteId)) {
            cmsPage.setSiteId(siteId);
        }

        if (StringUtils.isNoneBlank(templateId)) {
            cmsPage.setTemplateId(templateId);
        }

        if (StringUtils.isNoneBlank(pageAliase)) {
            cmsPage.setPageAliase(pageAliase);
        }

        Example<CmsPage> example = Example.of(cmsPage, exampleMatcher);

        if (page <= 0) {
            page = 1;
        }

        if (size <= 0) {
            size = 5;
        }

        PageRequest pageRequest = PageRequest.of(page - 1, size);

        Page<CmsPage> all = null;

        all = cmsPageDao.findAll(example, pageRequest);

        return new ResponseCmsPageList(all.getTotalElements(), all.toList());
    }

    /**
     * 向cms_page集合插入一条文档
     *
     * @param cmsPage 数据
     * @return 返回
     * Todo 时间有问题，数据库显示少了8个小时，读出来正常。。。先不解决
     */
    @Override
    public void insertCmsPage(CmsPage cmsPage) {
        // 要先判断是否存在相同的 在cms_page集中上创建页面名称、站点Id、页面webpath为唯一索引
        CmsPage index = cmsPageDao.findByPageNameAndSiteIdAndPageWebPath(
                cmsPage.getPageName(), cmsPage.getSiteId(), cmsPage.getPageWebPath());
        if (index != null) {
            throw new CustomException(CommonCode.FAILED);
        }

        // 不存在 执行插入
        CmsPage successData = cmsPageDao.insert(cmsPage);
        if (successData == null) {
            throw new CustomException(CommonCode.FAILED);
        }
    }

    /**
     * 通过id查询cmsPage
     *
     * @param id id
     * @return 数据
     */
    @Override
    public CmsPage queryCmsPageById(String id) {
        Optional<CmsPage> data = cmsPageDao.findById(id);
        return data.orElseThrow(() -> new CustomException(CommonCode.FAILED));
    }

    /**
     * 更新 cmsPage
     *
     * @param cmsPage 数据
     * @return 数据
     */
    @Override
    public void updateCmsPage(CmsPage cmsPage) {
        CmsPage save = cmsPageDao.save(cmsPage);
        if (save == null) {
            throw new CustomException(CommonCode.FAILED);
        }
    }

    /**
     * 通过id删除
     *
     * @param id id
     * @return 数据
     */
    @Override
    public void deleteCmsPageById(String id) {
        cmsPageDao.deleteById(id);
    }

    /**
     * @param id id
     * @return
     */
    @Override
    public String queryCmsPageHtmlById(String id) throws IOException, TemplateException {

        CmsPage cmsPage = this.queryCmsPageById(id);

        Map<String, String> map = this.getTemplateData(cmsPage);

        String templateString = this.getTemplate(cmsPage);

        return this.generateStaticHtml(templateString, map);
    }

    private Map<String, String> getTemplateData(CmsPage cmsPage){
        //1 获取模板数据
        String dataUrl = cmsPage.getDataUrl();

        if (dataUrl == null) {
            throw new CustomException(CommonCode.FAILED);
        }

        ResponseResult templateData = restTemplate.getForObject(dataUrl, ResponseResult.class);

        if (templateData == null || templateData.getStatus() != 200 || templateData.getData() == null) {
            throw new CustomException(CommonCode.FAILED);
        }

        return new HashMap<>((Map<String, String>) templateData.getData());
    }

    private String getTemplate(CmsPage cmsPage) throws IOException {
        //2 获取模板
        String templateId = cmsPage.getTemplateId();

        if (templateId == null) {
            throw new CustomException(CommonCode.FAILED);
        }

        CmsTemplate cmsTemplate = cmsTemplateService.queryCmsTemplateById(templateId);

        String templateFileId = cmsTemplate.getTemplateFileId();
        if (templateFileId == null) {
            throw new CustomException(CommonCode.FAILED);
        }

        GridFSFile gridFsFile = gridFsTemplate.findOne(Query.query(GridFsCriteria.where("_id").is(templateFileId)));
        if (gridFsFile == null) {
            throw new CustomException(CommonCode.FAILED);
        }

        GridFsResource resource = gridFsTemplate.getResource(gridFsFile);

        return IOUtils.toString(resource.getInputStream(), "utf-8");
    }

    private String generateStaticHtml(String templateString,Map<String, String> map) throws IOException, TemplateException {
        //3 生成静态页
        //创建配置类
        Configuration configuration = new Configuration(Configuration.getVersion());

        //加载模板
        //模板加载器
        StringTemplateLoader stringTemplateLoader = new StringTemplateLoader();
        stringTemplateLoader.putTemplate("template", templateString);
        configuration.setTemplateLoader(stringTemplateLoader);

        Template template = configuration.getTemplate("template", "utf-8");

        return FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
    }

    /**
     *
     * 需求
     * 1、前端请求cms执行页面发布。
     * 2、cms执行静态化程序生成html文件。
     * 3、cms将html文件存储到GridFS中。
     * 4、cms向MQ发送页面发布消息
     * 5、MQ将页面发布消息通知给Cms Client              mq_server
     * 6、Cms Client从GridFS中下载html文件              cms_client 处理
     * 7、Cms Client将html保存到所在服务器指定目录       cms_client 处理
     *
     * @param id cmsPage 页面
     * @throws IOException
     * @throws TemplateException
     */
    @Override
    public void saveCmsPageHtmlById(String id) throws IOException, TemplateException {

        //2、cms执行静态化程序生成html文件。
        CmsPage cmsPage = this.queryCmsPageById(id);

        Map<String, String> map = this.getTemplateData(cmsPage);

        String templateString = this.getTemplate(cmsPage);

        String staticHtml = this.generateStaticHtml(templateString, map);

        InputStream inputStream = IOUtils.toInputStream(staticHtml, "utf-8");

        //存储静态化后的 html 文件
        // 3、cms将html文件存储到GridFS中。
        // 静态页在 gridFs 作为中间存储，最终会持久化到硬盘
        // 出现异常，没必要同步删除，使用异步定时器定时清理静态页
        ObjectId objectId = gridFsTemplate.store(inputStream, "static_html");

        //设置 html file id
        cmsPage.setHtmlFileId(objectId.toString());

        CmsHtmlRetryMessage cmsHtmlRetryMessage = new CmsHtmlRetryMessage(cmsPage.getId());

        //保存
        // 出现异常，HtmlFileId不回退好像没什么问题
        updateCmsPage(cmsPage);

        // 出现异常，没必要同步删除，使用异步定时器定时清理
        cmsHtmlRetryMessageService.insertCmsHtmlRetryMessage(cmsHtmlRetryMessage);

        //发送
        //4、cms向MQ发送页面发布消息
        //出现异常，记录日志
        rabbitStaticHtmlSender.sendMessage(cmsHtmlRetryMessage,exchange,routingKey);
    }
}
