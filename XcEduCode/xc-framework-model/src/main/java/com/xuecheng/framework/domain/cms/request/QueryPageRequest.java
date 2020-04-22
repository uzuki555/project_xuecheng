package com.xuecheng.framework.domain.cms.request;

import io.swagger.annotations.ApiParam;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class QueryPageRequest {
    @ApiParam("站点ID")
    private String siteId;
    @ApiParam("页面名")
    private String pageName;
    @ApiParam("页面别名")
    private String pageAliase;
    @ApiParam("模板ID")
    private String templateId;
//    private String htmlFileId;
//    private String pageParams;
    @ApiParam("页面ID")
    @Id
    private String pageId;
    @ApiParam("页面类型")
    private  String pageType;
    @ApiParam("数据连接")
    private  String dataUrl;
}
