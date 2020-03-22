package com.xxnjdg.heimacms.servicemodel.cmsservice;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Map;

@ApiModel("CmsConfigModel实体类")
@Data
public class CmsConfigModel {
    @ApiModelProperty("主键")
    private String key;
    @ApiModelProperty("项目名称")
    private String name;
    @ApiModelProperty("项目url")
    private String url;
    @ApiModelProperty("项目复杂值")
    private Map mapValue;
    @ApiModelProperty("项目简单值")
    private String value;
}
