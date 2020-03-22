package com.xxnjdg.heimacms.servicemodel.cmsservice;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author djr
 * @version 1.0
 * @date 2020/3/15 14:39
 */
@ApiModel("sys_dictionary 系统字典选项实体")
@Data
public class SysDictionaryOption {
    @ApiModelProperty("名字")
    @Field("sd_name")
    private String sdName;

    @ApiModelProperty("id")
    @Field("sd_id")
    private String sdId;

    @ApiModelProperty("状态")
    @Field("sd_status")
    private String sdStatus;
}
