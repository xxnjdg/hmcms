package com.xxnjdg.heimacms.cmsservice.service;


import com.xxnjdg.heimacms.servicemodel.cmsservice.CmsSite;

import java.util.List;

/**
 * @author djr
 * @version 1.0
 * @date 2020/3/9 11:47
 */
public interface CmsSiteService {
    List<CmsSite> queryCmsSiteList();
}
