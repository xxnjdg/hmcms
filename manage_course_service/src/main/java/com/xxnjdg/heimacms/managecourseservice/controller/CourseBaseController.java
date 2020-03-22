package com.xxnjdg.heimacms.managecourseservice.controller;

import com.github.pagehelper.PageInfo;
import com.xxnjdg.heimacms.common.model.response.CommonCode;
import com.xxnjdg.heimacms.common.model.response.ResponseResult;
import com.xxnjdg.heimacms.managecourseservice.service.CourseBaseService;
import com.xxnjdg.heimacms.serviceapi.managecourseservice.CourseBaseControllerApi;
import com.xxnjdg.heimacms.servicemodel.managecourseservice.CourseBase;
import com.xxnjdg.heimacms.servicemodel.managecourseservice.response.CourseBaseAndCoursePicture;
import com.xxnjdg.heimacms.servicemodel.validatedgroup.Insert;
import com.xxnjdg.heimacms.servicemodel.validatedgroup.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

/**
 * @author djr
 * @version 1.0
 * @date 2020/3/14 19:06
 */
@RestController
public class CourseBaseController implements CourseBaseControllerApi {

    @Autowired
    private CourseBaseService courseBaseService;

    @Override
    @GetMapping("/course/base/picture/list/{page}/{size}")
    public ResponseResult queryCourseBaseAndCoursePictureList(@PathVariable("page") int page, @PathVariable("size") int size) {
        PageInfo<CourseBaseAndCoursePicture> courseBaseAndCoursePicturePageInfo = courseBaseService.queryCourseBaseAndCoursePictureList(page, size);
        return new ResponseResult(CommonCode.SUCCESS,courseBaseAndCoursePicturePageInfo);
    }

    @Override
    @GetMapping("/course/base/{id}")
    public ResponseResult queryCourseBaseById(@PathVariable("id")  String id) {
        CourseBase courseBase = courseBaseService.queryCourseBaseById(id);
        return new ResponseResult(CommonCode.SUCCESS,courseBase);
    }

    @Override
    @PostMapping("/course/base")
    public ResponseResult insertCourseBase(@RequestBody @Validated(Insert.class) CourseBase courseBase) {
        courseBaseService.insertCourseBase(courseBase);
        return new ResponseResult(CommonCode.SUCCESS,null);
    }

    @Override
    @PutMapping("/course/base")
    public ResponseResult updateCourseBase(@RequestBody @Validated(Update.class) CourseBase courseBase) {
        courseBaseService.updateCourseBase(courseBase);
        return new ResponseResult(CommonCode.SUCCESS,null);
    }
}
