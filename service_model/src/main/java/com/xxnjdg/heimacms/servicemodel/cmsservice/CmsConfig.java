package com.xxnjdg.heimacms.servicemodel.cmsservice;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@ApiModel("cms_config实体类，储存模板数据")
@Document("cms_config")
@Data
public class CmsConfig {
    @Id
    @ApiModelProperty("主键")
    private String id;
    @ApiModelProperty("数据模型名称")
    private String name;
    @ApiModelProperty("数据模型项目")
    private List<CmsConfigModel> model;
}
