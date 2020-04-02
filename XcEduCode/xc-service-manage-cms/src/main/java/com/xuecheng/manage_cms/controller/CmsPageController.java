package com.xuecheng.manage_cms.controller;

import com.xuecheng.api.cms.CmsPageControllerApi;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.manage_cms.service.CmsPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cms/mange/")

public class CmsPageController  implements CmsPageControllerApi {
    @Autowired
    public CmsPageService cmsPageService;
    @Override
    @GetMapping("list/{page}/{size}")

    public QueryResponseResult findList(@PathVariable("page") int page, @PathVariable("size")int size, QueryPageRequest queryPageRequest) {
//        CmsPage cmsPage = new CmsPage();
//        cmsPage.setPageName("测试页面");
//        List<CmsPage> cmsPages = new LinkedList<>();
//        cmsPages.add(cmsPage);
//        QueryResult queryResult = new QueryResult();
//        queryResult.setList(cmsPages);
//        queryResult.setTotal(1);
//        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS,queryResult);
        return cmsPageService.findList(page,size,queryPageRequest);
    }
}
