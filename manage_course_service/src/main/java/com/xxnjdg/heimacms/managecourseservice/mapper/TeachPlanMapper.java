package com.xxnjdg.heimacms.managecourseservice.mapper;

import com.xxnjdg.heimacms.servicemodel.managecourseservice.TeachPlan;
import com.xxnjdg.heimacms.servicemodel.managecourseservice.response.TeachPlanTree;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author djr
 * @version 1.0
 * @date 2020/3/13 17:50
 */
@Mapper
public interface TeachPlanMapper {
    /**
     * 获取 TeachPlan 列表
     * @return
     */
    List<TeachPlan> queryTeachPlanList();


    /**
     * 获取 TeachPlanTree
     *
     * @param id 主键
     * @return TeachPlanTree list
     */
    List<TeachPlanTree> queryTeachPlanTreeListById(String id);

    /**
     * 根据 父节点id 和 课程id 查询教学计划
     * @param parentId 父节点id
     * @param courseId 课程id
     * @return 实体类
     */
    TeachPlan queryTeachPlanByParentIdAndCourseId(@Param("parentId") String parentId, @Param("courseId") String courseId);


    /**
     * 查询
     * @param id 主键
     * @param grade 等级
     * @param courseId 课程id
     * @return 实体类
     */
    TeachPlan queryTeachPlanByIdAndGradeAndCourseId(@Param("id") String id, @Param("grade") String grade,@Param("courseId") String courseId);

    /**
     * 插入课程计划
     * @param teachPlan 实体类
     * @return >0 success <=0 fail
     */
    int insertTeachPlan(TeachPlan teachPlan);

    /**
     * 更新课程计划
     * @param teachPlan 实体类
     * @return >0 success <=0 fail
     */
    int updateTeachPlan(TeachPlan teachPlan);

    /**
     * 根据id查询
     * @param id 主键
     * @return 实体类
     */
    TeachPlan queryTeachPlanById(String id);

}
