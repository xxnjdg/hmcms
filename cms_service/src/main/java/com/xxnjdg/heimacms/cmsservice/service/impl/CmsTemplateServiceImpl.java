package com.xxnjdg.heimacms.cmsservice.service.impl;

import com.xxnjdg.heimacms.cmsservice.dao.CmsTemplateDao;
import com.xxnjdg.heimacms.cmsservice.service.CmsTemplateService;
import com.xxnjdg.heimacms.common.exception.CustomException;
import com.xxnjdg.heimacms.common.model.response.CommonCode;
import com.xxnjdg.heimacms.servicemodel.cmsservice.CmsTemplate;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * @author djr
 * @version 1.0
 * @date 2020/3/9 11:47
 */
@Service
public class CmsTemplateServiceImpl implements CmsTemplateService {

    @Autowired
    private CmsTemplateDao cmsTemplateDao;

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Override
    public List<CmsTemplate> queryCmsTemplateList() {
        return cmsTemplateDao.findAll();
    }

    /**
     * 1 上传文件，获取文件id
     * 2 插入CmsTemplate
     * @param file 文件
     * @param siteId
     * @param templateName
     * @param templateParameter
     * @return
     */
    @Override
    public void insertCmsTemplate(MultipartFile file, String siteId, String templateName, String templateParameter) {

        if (file.isEmpty()){
            throw new CustomException(CommonCode.FAILED);
        }

//        1 上传文件，获取文件id
        ObjectId id;
        try {
            id = gridFsTemplate.store(file.getInputStream(), file.getName());
        } catch (IOException e) {
            e.printStackTrace();
            throw new CustomException(CommonCode.FAILED);
        }

        CmsTemplate cmsTemplate = new CmsTemplate();
        cmsTemplate.setSiteId(siteId);
        cmsTemplate.setTemplateName(templateName);
        cmsTemplate.setTemplateParameter(templateParameter);
        cmsTemplate.setTemplateFileId(id.toString());

        CmsTemplate insert = cmsTemplateDao.insert(cmsTemplate);
        if (insert == null){
            throw new CustomException(CommonCode.FAILED);
        }

    }

    /**
     *
     * @param id id
     * @return
     */
    @Override
    public CmsTemplate queryCmsTemplateById(String id) {
        Optional<CmsTemplate> data = cmsTemplateDao.findById(id);
        return data.orElseThrow(() -> new CustomException(CommonCode.FAILED));
    }
}
