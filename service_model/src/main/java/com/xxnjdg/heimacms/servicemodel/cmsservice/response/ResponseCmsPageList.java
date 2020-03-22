package com.xxnjdg.heimacms.servicemodel.cmsservice.response;

import com.xxnjdg.heimacms.servicemodel.cmsservice.CmsPage;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ResponseCmsPageList {
    private Long totalElements;
    private List<CmsPage> totalData;
}
