package com.xxnjdg.heimacms.managecourseservice.service.impl;

import com.xxnjdg.heimacms.managecourseservice.mapper.CourseCategoryMapper;
import com.xxnjdg.heimacms.managecourseservice.service.CourseCategoryService;
import com.xxnjdg.heimacms.servicemodel.managecourseservice.response.CourseCategoryTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author djr
 * @version 1.0
 * @date 2020/3/15 18:09
 */
@Service
public class CourseCategoryServiceImpl implements CourseCategoryService {

    @Autowired
    private CourseCategoryMapper courseCategoryMapper;

    @Override
    public List<CourseCategoryTree> queryCourseCategoryTreeList() {
        return courseCategoryMapper.queryCourseCategoryTreeList();
    }
}
