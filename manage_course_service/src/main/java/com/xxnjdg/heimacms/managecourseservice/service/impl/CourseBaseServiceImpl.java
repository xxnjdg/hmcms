package com.xxnjdg.heimacms.managecourseservice.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxnjdg.heimacms.common.exception.CustomException;
import com.xxnjdg.heimacms.common.model.response.CommonCode;
import com.xxnjdg.heimacms.managecourseservice.mapper.CourseBaseMapper;
import com.xxnjdg.heimacms.managecourseservice.service.CourseBaseService;
import com.xxnjdg.heimacms.servicemodel.managecourseservice.CourseBase;
import com.xxnjdg.heimacms.servicemodel.managecourseservice.response.CourseBaseAndCoursePicture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author djr
 * @version 1.0
 * @date 2020/3/14 19:11
 */
@Service
@Slf4j
public class CourseBaseServiceImpl implements CourseBaseService {

    @Autowired
    private CourseBaseMapper courseBaseMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public PageInfo<CourseBaseAndCoursePicture> queryCourseBaseAndCoursePictureList(int page, int size) {
        PageHelper.startPage(page, size);
        List<CourseBaseAndCoursePicture> courseBaseAndCoursePictures = courseBaseMapper.queryCourseBaseAndCoursePictureList(page, size);
        return new PageInfo<>(courseBaseAndCoursePictures);
    }

    @Override
    public CourseBase queryCourseBaseById(String id) {
        CourseBase courseBase = courseBaseMapper.queryCourseBaseById(id);
        if(courseBase == null){
            throw new CustomException(CommonCode.FAILED);
        }
        return courseBase;
    }

    @Override
    public void insertCourseBase(CourseBase courseBase) {
        // TODO: 2020/3/17 rpc没做校验
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://192.168.100.33:10030/api/segment/get/leaf-segment-test", String.class);
        String body = forEntity.getBody();
        courseBase.setId(body);

        int isSucceed = courseBaseMapper.insertCourseBase(courseBase);
        if (isSucceed <= 0) {
            throw new CustomException(CommonCode.FAILED);
        }
    }

    @Override
    public void updateCourseBase(CourseBase courseBase) {
        int isSucceed = courseBaseMapper.updateCourseBase(courseBase);
        if (isSucceed <= 0) {
            throw new CustomException(CommonCode.FAILED);
        }
    }
}
