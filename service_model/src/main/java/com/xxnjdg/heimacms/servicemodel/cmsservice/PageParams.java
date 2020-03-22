package com.xxnjdg.heimacms.servicemodel.cmsservice;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@ApiModel("PageParams实体")
@Data
@NoArgsConstructor
public class PageParams {
    private String pageParamName;
    private String pageParamValue;
}
