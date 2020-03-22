package com.xxnjdg.heimacms.cmsservice.service;

import com.xxnjdg.heimacms.servicemodel.cmsservice.SysDictionary;

import java.util.List;

/**
 * @author djr
 * @version 1.0
 * @date 2020/3/15 14:55
 */
public interface SysDictionaryService {
    /**
     * xx
     * @return
     */
    List<SysDictionary> querySysDictionaryList();

    /**
     * xx
     * @param dName xx
     * @return xx
     */
    SysDictionary querySysDictionaryBydName(String dName);
}
