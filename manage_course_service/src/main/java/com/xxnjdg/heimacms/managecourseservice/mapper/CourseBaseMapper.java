package com.xxnjdg.heimacms.managecourseservice.mapper;

import com.xxnjdg.heimacms.servicemodel.managecourseservice.CourseBase;
import com.xxnjdg.heimacms.servicemodel.managecourseservice.response.CourseBaseAndCoursePicture;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author djr
 * @version 1.0
 * @date 2020/3/14 19:12
 */
@Mapper
public interface CourseBaseMapper {
    /**
     * 查询基本课程信息，支持分页
     * @param page 第几页
     * @param size 页数
     * @return 列表
     */
    List<CourseBaseAndCoursePicture> queryCourseBaseAndCoursePictureList(int page, int size);

    /**
     * 根据id查询课程基本信息
     * @param id id
     * @return 基本信息
     */
    CourseBase queryCourseBaseById(String id);

    /**
     * 添加课程基本信息
     * @param courseBase 实体类
     * @return >0 success <=0 fail
     */
    int insertCourseBase(CourseBase courseBase);

    /**
     * 更新课程基本信息
     * @param courseBase 实体类
     * @return >0 success <=0 fail
     */
    int updateCourseBase(CourseBase courseBase);
}
