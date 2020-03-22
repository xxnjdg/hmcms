package com.xxnjdg.heimacms.servicemodel.managecourseservice.response;

import com.xxnjdg.heimacms.servicemodel.managecourseservice.TeachPlan;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author djr
 * @version 1.0
 * @date 2020/3/17 1:55
 */
@Data
@ApiModel("教学计划树状实体类")
public class TeachPlanTree extends TeachPlan {
    @ApiModelProperty("子节点")
    private List<TeachPlanTree> children;
}
