package com.xxnjdg.heimacms.managecourseservice.service;

import com.github.pagehelper.PageInfo;
import com.xxnjdg.heimacms.servicemodel.managecourseservice.CourseBase;
import com.xxnjdg.heimacms.servicemodel.managecourseservice.response.CourseBaseAndCoursePicture;

/**
 * @author djr
 * @version 1.0
 * @date 2020/3/14 19:08
 */
public interface CourseBaseService {
    /**
     * 查询基本课程信息，支持分页
     * @param page 第几页
     * @param size 页数
     * @return 列表
     */
    PageInfo<CourseBaseAndCoursePicture> queryCourseBaseAndCoursePictureList(int page, int size);

    /**
     * 根据id查询课程基本信息
     * @param id id
     */
    CourseBase queryCourseBaseById(String id);

    /**
     * 添加课程基本信息
     * @param courseBase 实体类
     */
    void insertCourseBase(CourseBase courseBase);

    /**
     * 更新课程基本信息
     * @param courseBase 实体类
     */
    void updateCourseBase(CourseBase courseBase);
}
