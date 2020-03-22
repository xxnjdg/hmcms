package com.xxnjdg.heimacms.cmsservice.service.impl;

import com.xxnjdg.heimacms.cmsservice.dao.CmsSiteDao;
import com.xxnjdg.heimacms.cmsservice.service.CmsSiteService;
import com.xxnjdg.heimacms.servicemodel.cmsservice.CmsSite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author djr
 * @version 1.0
 * @date 2020/3/9 11:47
 */
@Service
public class CmsSiteServiceImpl implements CmsSiteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CmsConfigServiceImpl.class);

    @Autowired
    private CmsSiteDao cmsSiteDao;

    @Override
    public List<CmsSite> queryCmsSiteList() {
        return cmsSiteDao.findAll();
    }
}
