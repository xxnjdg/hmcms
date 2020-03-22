package com.xxnjdg.heimacms.cmsservice.service.impl;

import com.xxnjdg.heimacms.cmsservice.dao.CmsHtmlRetryMessageDao;
import com.xxnjdg.heimacms.cmsservice.service.CmsHtmlRetryMessageService;
import com.xxnjdg.heimacms.common.exception.CustomException;
import com.xxnjdg.heimacms.common.model.response.CommonCode;
import com.xxnjdg.heimacms.servicemodel.cmsservice.CmsHtmlRetryMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @author djr
 * @version 1.0
 * @date 2020/3/9 11:47
 */
@Service
public class CmsHtmlRetryMessageServiceImpl implements CmsHtmlRetryMessageService {

    @Autowired
    private CmsHtmlRetryMessageDao cmsHtmlRetryMessageDao;

    @Override
    public List<CmsHtmlRetryMessage> queryCmsHtmlRetryMessageListByStatus(String status) {
        CmsHtmlRetryMessage cmsHtmlRetryMessage = new CmsHtmlRetryMessage();
        cmsHtmlRetryMessage.setStatus(status);

        Example<CmsHtmlRetryMessage> example = Example.of(cmsHtmlRetryMessage);

        return cmsHtmlRetryMessageDao.findAll(example);
    }

    @Override
    public CmsHtmlRetryMessage queryCmsHtmlRetryMessageById(String id) {
        return cmsHtmlRetryMessageDao.findById(id).orElseThrow(() -> new CustomException(CommonCode.FAILED));
    }

    @Override
    public void insertCmsHtmlRetryMessage(CmsHtmlRetryMessage cmsHtmlRetryMessage) {
        CmsHtmlRetryMessage successData = cmsHtmlRetryMessageDao.insert(cmsHtmlRetryMessage);
        if (successData == null) {
            throw new CustomException(CommonCode.FAILED);
        }
    }

    @Override
    public void updateCmsHtmlRetryMessage(CmsHtmlRetryMessage cmsHtmlRetryMessage) {
        CmsHtmlRetryMessage save = cmsHtmlRetryMessageDao.save(cmsHtmlRetryMessage);
        if (save == null) {
            throw new CustomException(CommonCode.FAILED);
        }
    }


}
