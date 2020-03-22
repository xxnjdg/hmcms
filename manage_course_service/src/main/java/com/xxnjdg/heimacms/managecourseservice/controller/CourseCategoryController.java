package com.xxnjdg.heimacms.managecourseservice.controller;

import com.xxnjdg.heimacms.common.model.response.CommonCode;
import com.xxnjdg.heimacms.common.model.response.ResponseResult;
import com.xxnjdg.heimacms.managecourseservice.service.CourseCategoryService;
import com.xxnjdg.heimacms.serviceapi.managecourseservice.CourseCategoryControllerApi;
import com.xxnjdg.heimacms.servicemodel.managecourseservice.response.CourseCategoryTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author djr
 * @version 1.0
 * @date 2020/3/15 18:05
 */
@RestController
public class CourseCategoryController implements CourseCategoryControllerApi {

    @Autowired
    private CourseCategoryService courseCategoryService;

    @Override
    @GetMapping("/course/category/tree/list")
    public ResponseResult queryCourseCategoryTreeList() {
        List<CourseCategoryTree> courseCategoryTrees = courseCategoryService.queryCourseCategoryTreeList();
        return new ResponseResult(CommonCode.SUCCESS,courseCategoryTrees);
    }
}
