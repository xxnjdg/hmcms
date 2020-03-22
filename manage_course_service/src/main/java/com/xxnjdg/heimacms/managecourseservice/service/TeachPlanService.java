package com.xxnjdg.heimacms.managecourseservice.service;

import com.xxnjdg.heimacms.servicemodel.managecourseservice.TeachPlan;
import com.xxnjdg.heimacms.servicemodel.managecourseservice.response.TeachPlanTree;

import java.util.List;

/**
 * @author djr
 * @version 1.0
 * @date 2020/3/13 18:37
 */
public interface TeachPlanService {
    /**
     * 获取 TeachPlan 列表
     *
     * @return
     */
    List<TeachPlan> queryTeachPlanList();


    /**
     * 获取 TeachPlanTree list
     *
     * @param id 主键
     * @return TeachPlanTree list
     */
    List<TeachPlanTree> queryTeachPlanTreeListById(String id);

    /**
     * 根据id查询
     *
     * @param id 主键
     * @return 实体类
     */
    TeachPlan queryTeachPlanById(String id);

    /**
     * 插入课程计划
     *
     * @param teachPlan 实体类
     */
    void insertTeachPlan(TeachPlan teachPlan);


    /**
     * 根据id,grade,courseId查询
     *
     * @param id       主键
     * @param grade    等级
     * @param courseId 课程id
     * @return 实体类
     */
    TeachPlan queryTeachPlanByIdAndGradeAndCourseId(String id, String grade, String courseId);

    /**
     * 更新操作
     *
     * @param teachPlan 实体类
     */
    void updateTeachPlan(TeachPlan teachPlan);


}
