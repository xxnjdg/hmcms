package com.xxnjdg.heimacms.cmsservice.controller;

import com.xxnjdg.heimacms.cmsservice.service.SysDictionaryService;
import com.xxnjdg.heimacms.common.model.response.CommonCode;
import com.xxnjdg.heimacms.common.model.response.ResponseResult;
import com.xxnjdg.heimacms.serviceapi.cmsservice.SysDictionaryControllerApi;
import com.xxnjdg.heimacms.servicemodel.cmsservice.SysDictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author djr
 * @version 1.0
 * @date 2020/3/15 14:54
 */
@RestController
public class SysDictionaryController implements SysDictionaryControllerApi {

    @Autowired
    private SysDictionaryService sysDictionaryService;

    @Override
    @GetMapping("/sys/dictionary/list")
    public ResponseResult querySysDictionaryList() {
        List<SysDictionary> sysDictionaries = sysDictionaryService.querySysDictionaryList();
        return new ResponseResult(CommonCode.SUCCESS,sysDictionaries);
    }

    @Override
    @GetMapping("/sys/dictionary")
    public ResponseResult querySysDictionaryBydName(String dName) {
        SysDictionary sysDictionary = sysDictionaryService.querySysDictionaryBydName(dName);
        return new ResponseResult(CommonCode.SUCCESS,sysDictionary);
    }
}
