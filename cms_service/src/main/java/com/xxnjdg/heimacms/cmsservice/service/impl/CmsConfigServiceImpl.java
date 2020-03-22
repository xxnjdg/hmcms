package com.xxnjdg.heimacms.cmsservice.service.impl;

import com.xxnjdg.heimacms.cmsservice.dao.CmsConfigDao;
import com.xxnjdg.heimacms.cmsservice.service.CmsConfigService;
import com.xxnjdg.heimacms.common.exception.CustomException;
import com.xxnjdg.heimacms.common.model.response.CommonCode;
import com.xxnjdg.heimacms.servicemodel.cmsservice.CmsConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
/**
 * @author djr
 * @version 1.0
 * @date 2020/3/9 11:47
 */
@Service
public class CmsConfigServiceImpl implements CmsConfigService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CmsConfigServiceImpl.class);

    @Autowired
    private CmsConfigDao cmsConfigDao;

    /**
     * 通过主键查询
     * @param id 主键
     * @return  ResponseResult
     */
    @Override
    public CmsConfig queryCmsConfigById(String id) {
        Optional<CmsConfig> data = cmsConfigDao.findById(id);
        return data.orElseThrow(() -> new CustomException(CommonCode.FAILED));
    }
}
