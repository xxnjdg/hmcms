package com.xxnjdg.heimacms.cmsservice.dao;

import com.xxnjdg.heimacms.servicemodel.cmsservice.SysDictionary;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author djr
 * @version 1.0
 * @date 2020/3/15 14:57
 */
@Repository
public interface SysDictionaryDao extends MongoRepository<SysDictionary,String> {
    /**
     * 根据数据字典名字查询SysDictionary
     * @param dName 数据字典名字
     * @return
     */
    SysDictionary queryBydName(String dName);
}
