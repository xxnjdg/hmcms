package com.xxnjdg.heimacms.servicemodel.cmsservice;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@ApiModel("cms_site 实体")
@Document(collection = "cms_site")
@Data
@NoArgsConstructor
public class CmsSite {
    @Id
    @ApiModelProperty("id")
    private String id;
    @ApiModelProperty("网站名字")
    private String siteName;
    @ApiModelProperty("网站域名")
    private String siteDomain;
    @ApiModelProperty("网站端口")
    private String sitePort;
    @ApiModelProperty("网站访问路径")
    private String siteWebPath;
    @ApiModelProperty("网站创建时间")
    private Date siteCreateTime;
}
