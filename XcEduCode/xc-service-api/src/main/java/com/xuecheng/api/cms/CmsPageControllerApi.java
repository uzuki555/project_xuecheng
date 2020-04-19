package com.xuecheng.api.cms;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value = "cms页面管理",description = "cms页面管理接口，提供页面增删改查")
public interface CmsPageControllerApi {
    @ApiOperation("cms页面寻找接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path",name="page",required = true,value = "页码",dataType = "int"),
            @ApiImplicitParam(paramType = "path",name="size",required = true,value = "每页显示记录数",dataType = "int")
    })
    public  QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest);


    @ApiOperation("新增页面")
    public CmsPageResult addCmsPage(CmsPage cmsPage);
}
