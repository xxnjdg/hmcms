package com.xxnjdg.heimacms.managecourseservice.mapper;

import com.xxnjdg.heimacms.servicemodel.managecourseservice.response.CourseCategoryTree;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author djr
 * @version 1.0
 * @date 2020/3/15 18:10
 */
@Mapper
public interface CourseCategoryMapper {

    /**
     * sx
     * @return
     */
    List<CourseCategoryTree> queryCourseCategoryTreeList();
}
