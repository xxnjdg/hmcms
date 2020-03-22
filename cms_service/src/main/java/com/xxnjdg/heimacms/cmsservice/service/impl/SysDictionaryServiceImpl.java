package com.xxnjdg.heimacms.cmsservice.service.impl;

import com.xxnjdg.heimacms.cmsservice.dao.SysDictionaryDao;
import com.xxnjdg.heimacms.cmsservice.service.SysDictionaryService;
import com.xxnjdg.heimacms.servicemodel.cmsservice.SysDictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author djr
 * @version 1.0
 * @date 2020/3/15 14:56
 */
@Service
public class SysDictionaryServiceImpl implements SysDictionaryService {

    @Autowired
    private SysDictionaryDao sysDictionaryDao;

    @Override
    public List<SysDictionary> querySysDictionaryList() {
        return sysDictionaryDao.findAll();
    }

    @Override
    public SysDictionary querySysDictionaryBydName(String dName) {
        return sysDictionaryDao.queryBydName(dName);
    }
}
