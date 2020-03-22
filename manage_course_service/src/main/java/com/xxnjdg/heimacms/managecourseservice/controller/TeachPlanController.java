package com.xxnjdg.heimacms.managecourseservice.controller;

import com.xxnjdg.heimacms.common.model.response.CommonCode;
import com.xxnjdg.heimacms.common.model.response.ResponseResult;
import com.xxnjdg.heimacms.managecourseservice.service.TeachPlanService;
import com.xxnjdg.heimacms.serviceapi.managecourseservice.TeachPlanControllerApi;
import com.xxnjdg.heimacms.servicemodel.managecourseservice.TeachPlan;
import com.xxnjdg.heimacms.servicemodel.managecourseservice.response.TeachPlanTree;
import com.xxnjdg.heimacms.servicemodel.validatedgroup.Insert;
import com.xxnjdg.heimacms.servicemodel.validatedgroup.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author djr
 * @version 1.0
 * @date 2020/3/13 18:34
 */
@RestController
public class TeachPlanController implements TeachPlanControllerApi {

    @Autowired
    private TeachPlanService teachPlanService;


    @Override
    @GetMapping("/teach/test")
    public ResponseResult queryTeachPlanList() {
        List<TeachPlan> teachPlans = teachPlanService.queryTeachPlanList();
        return new ResponseResult(CommonCode.SUCCESS,teachPlans);
    }

    @Override
    @GetMapping("/teach/plan/tree/list/{id}")
    public ResponseResult queryTeachPlanTreeListById(@PathVariable("id") String id) {
        List<TeachPlanTree> teachPlanTrees = teachPlanService.queryTeachPlanTreeListById(id);
        return new ResponseResult(CommonCode.SUCCESS,teachPlanTrees);
    }

    @Override
    @GetMapping("/teach/plan/{id}")
    public ResponseResult queryTeachPlanById(@PathVariable("id") String id) {
        TeachPlan teachPlan = teachPlanService.queryTeachPlanById(id);
        return new ResponseResult(CommonCode.SUCCESS,teachPlan);
    }

    @Override
    @PostMapping("/teach/plan")
    public ResponseResult insertTeachPlan(@Validated(Insert.class) @RequestBody TeachPlan teachPlan) {
        teachPlanService.insertTeachPlan(teachPlan);
        return new ResponseResult(CommonCode.SUCCESS,null);
    }

    @Override
    @PutMapping("/teach/plan")
    public ResponseResult updateTeachPlan(@RequestBody @Validated(Update.class) TeachPlan teachPlan) {
        teachPlanService.updateTeachPlan(teachPlan);
        return new ResponseResult(CommonCode.SUCCESS,null);
    }

}
