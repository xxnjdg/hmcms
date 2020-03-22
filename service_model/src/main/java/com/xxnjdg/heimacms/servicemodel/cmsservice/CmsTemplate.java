package com.xxnjdg.heimacms.servicemodel.cmsservice;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@ApiModel("cms_template 实体")
@Document(collection = "cms_template")
@Data
@NoArgsConstructor
public class CmsTemplate {
    @Id
    @ApiModelProperty("id")
    private String id;
    @ApiModelProperty("网站id")
    private String siteId;
    @ApiModelProperty("模板名字")
    private String templateName;
    private String templateParameter;
    @ApiModelProperty("模板文件id,fs.chunks的id")
    private String templateFileId;
}
