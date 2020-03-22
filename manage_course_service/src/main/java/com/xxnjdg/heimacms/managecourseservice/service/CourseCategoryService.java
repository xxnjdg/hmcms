package com.xxnjdg.heimacms.managecourseservice.service;

import com.xxnjdg.heimacms.servicemodel.managecourseservice.response.CourseCategoryTree;

import java.util.List;

/**
 * @author djr
 * @version 1.0
 * @date 2020/3/15 18:06
 */
public interface CourseCategoryService {
    /**
     * sx
     * @return
     */
    List<CourseCategoryTree> queryCourseCategoryTreeList();
}
