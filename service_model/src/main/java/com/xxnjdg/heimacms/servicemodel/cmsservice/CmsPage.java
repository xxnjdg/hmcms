package com.xxnjdg.heimacms.servicemodel.cmsservice;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@ApiModel("cms_page实体")
@Document(collection = "cms_page")
@Data
@NoArgsConstructor
public class CmsPage {
    @Id
    @ApiModelProperty("id")
    private String id;
    @ApiModelProperty("站点id")
    private String siteId;
    @ApiModelProperty("页面名称")
    private String pageName;
    @ApiModelProperty("页面别名")
    private String pageAliase;
    @ApiModelProperty("页面访问路径")
    private String pageWebPath;
    @ApiModelProperty("页面物理路径")
    private String pagePhysicalPath;
    @ApiModelProperty("页面类型")
    private String pageType;
    @ApiModelProperty("页面创建时间")
    private Date pageCreateTime;
    @ApiModelProperty("模板id")
    private String templateId;
    @ApiModelProperty("模板数据url")
    private String dataUrl;
    @ApiModelProperty("html静态页id,fs.chunks的id")
    private String htmlFileId;
    private List<PageParams> pageParams;
    private String pageParameter;
}
