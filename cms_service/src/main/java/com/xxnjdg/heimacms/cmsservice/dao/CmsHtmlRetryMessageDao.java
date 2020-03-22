package com.xxnjdg.heimacms.cmsservice.dao;


import com.xxnjdg.heimacms.servicemodel.cmsservice.CmsHtmlRetryMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
/**
 * @author djr
 * @version 1.0
 * @date 2020/3/9 11:47
 */
@Repository
public interface CmsHtmlRetryMessageDao extends MongoRepository<CmsHtmlRetryMessage,String> {
}
