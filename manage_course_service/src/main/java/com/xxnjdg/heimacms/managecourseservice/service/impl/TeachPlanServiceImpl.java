package com.xxnjdg.heimacms.managecourseservice.service.impl;

import com.xxnjdg.heimacms.common.exception.CustomException;
import com.xxnjdg.heimacms.common.model.response.CommonCode;
import com.xxnjdg.heimacms.managecourseservice.mapper.TeachPlanMapper;
import com.xxnjdg.heimacms.managecourseservice.service.CourseBaseService;
import com.xxnjdg.heimacms.managecourseservice.service.TeachPlanService;
import com.xxnjdg.heimacms.servicemodel.managecourseservice.CourseBase;
import com.xxnjdg.heimacms.servicemodel.managecourseservice.TeachPlan;
import com.xxnjdg.heimacms.servicemodel.managecourseservice.response.TeachPlanTree;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author djr
 * @version 1.0
 * @date 2020/3/13 18:42
 */
@Service
public class TeachPlanServiceImpl implements TeachPlanService {


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TeachPlanMapper teachPlanMapper;

    @Autowired
    private CourseBaseService courseBaseService;


    @Override
    public List<TeachPlan> queryTeachPlanList() {
        return teachPlanMapper.queryTeachPlanList();
    }

    @Override
    public List<TeachPlanTree> queryTeachPlanTreeListById(String id) {
        return teachPlanMapper.queryTeachPlanTreeListById(id);
    }

    @Override
    public void insertTeachPlan(TeachPlan teachPlan) {

        String courseId = teachPlan.getCourseId();

        //根据 parentId 查询父 TeachPlan 保证有效，有两种情况
        String parentId = teachPlan.getParentId();

        //查询课程基本信息保证 courseId 有效
        courseBaseService.queryCourseBaseById(courseId);

        //parentId 为空
        if (StringUtils.isBlank(parentId)) {

            //第一层
            teachPlan.setParentId("0");
            teachPlan.setGrade("1");
        } else {

            //parentId 存在，孩子节点要成为第2级
            // 查询父节点是否是第1级，同是保证 parentid 有效
            this.queryTeachPlanByIdAndGradeAndCourseId(parentId,"1",courseId);
            teachPlan.setGrade("2");
        }

        //设置主键
        teachPlan.setId(getPrimaryKeyByRpc());

        //插入
        int isSucceed = teachPlanMapper.insertTeachPlan(teachPlan);

        if (isSucceed <= 0) {
            throw new CustomException(CommonCode.FAILED);
        }

    }

    @Override
    public TeachPlan queryTeachPlanByIdAndGradeAndCourseId(String id, String grade, String courseId) {
        TeachPlan teachPlan = teachPlanMapper.queryTeachPlanByIdAndGradeAndCourseId(id, grade, courseId);
        if (teachPlan == null) {
            throw new CustomException(CommonCode.FAILED);
        }
        return teachPlan;
    }

    @Override
    public void updateTeachPlan(TeachPlan teachPlan) {
        int isSucceed = teachPlanMapper.updateTeachPlan(teachPlan);
        if (isSucceed <= 0) {
            throw new CustomException(CommonCode.FAILED);
        }
    }

    @Override
    public TeachPlan queryTeachPlanById(String id) {
        TeachPlan teachPlan = teachPlanMapper.queryTeachPlanById(id);
        if (teachPlan == null) {
            throw new CustomException(CommonCode.FAILED);
        }
        return teachPlan;
    }

    private String getPrimaryKeyByRpc() {
        // TODO: 2020/3/17 rpc没处理
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://192.168.100.33:10030/api/segment/get/leaf-segment-test", String.class);
        return forEntity.getBody();
    }


    private void insertTeachPlan2(TeachPlan teachPlan) {

        String courseId = teachPlan.getCourseId();

        //查询课程基本信息保证 courseId 有效
        //按照逻辑，应该是现有课程，再有教学计划
        CourseBase courseBase = courseBaseService.queryCourseBaseById(courseId);

        //根据 parentId 查询父 TeachPlan 保证有效，有两种情况
        String parentId = teachPlan.getParentId();

        //parentId 为空
        if (StringUtils.isBlank(parentId)) {

            //查询跟节点作为默认父节点
            TeachPlan teachPlan1 = teachPlanMapper.queryTeachPlanByParentIdAndCourseId("0", courseId);
            //根节点不存在，创键新节点
            if (teachPlan1 == null) {
                //先获取课程基本信息

                //构造教学计划
                teachPlan1 = new TeachPlan();
                teachPlan1.setId(getPrimaryKeyByRpc());
                teachPlan1.setPlanName(courseBase.getName());
                teachPlan1.setParentId("0");
                teachPlan1.setGrade("1");
                teachPlan1.setCourseId(courseId);
                teachPlan1.setStatus("0");

                //插入新的父节点
                int isSucceed = teachPlanMapper.insertTeachPlan(teachPlan1);

                if (isSucceed <= 0) {
                    throw new CustomException(CommonCode.FAILED);
                }

            }
            //设置孩子parentId
            teachPlan.setParentId(teachPlan1.getId());
            teachPlan.setGrade("2");
        } else {
            //parentId 存在，孩子节点要成为第三级
            // 查询父节点是否是第二级，同是保证 parentid 有效
            TeachPlan teachPlan1 = teachPlanMapper.queryTeachPlanByIdAndGradeAndCourseId(parentId, "2", courseId);
            if (teachPlan1 == null) {
                throw new CustomException(CommonCode.FAILED);
            }
            teachPlan.setGrade("3");
        }

        //设置主键
        teachPlan.setId(getPrimaryKeyByRpc());

        //插入
        int isSucceed = teachPlanMapper.insertTeachPlan(teachPlan);

        if (isSucceed <= 0) {
            throw new CustomException(CommonCode.FAILED);
        }

    }
}
