package com.xuecheng.manage_cms.controller;

import com.xuecheng.api.cms.CmsPageControllerApi;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.exception.CustomerException;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.manage_cms.service.CmsPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cms/manage/")

public class CmsPageController  implements CmsPageControllerApi {
    @Autowired
    public CmsPageService cmsPageService;
    @Override
    @GetMapping("/list/{page}/{size}")

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

    @Override
    @PostMapping("add")
    public CmsPageResult addCmsPage(@RequestBody CmsPage cmsPage) throws CustomerException {
        CmsPageResult cmsPageResult = cmsPageService.addCmsPage(cmsPage);
        return cmsPageResult;
    }

    @Override
    @GetMapping("/get/{pageId}")
    public CmsPage findByPageId(@PathVariable("pageId") String pageId) {
        CmsPage  cmsPage= cmsPageService.findByPageId(pageId);
        return cmsPage;
    }

    @Override
    @PutMapping("/edit/{pageId}")
    public CmsPageResult editByPageId(@PathVariable("pageId") String pageId,@RequestBody CmsPage cmsPage) throws CustomerException {
        CmsPageResult cmsPageResult = cmsPageService.editCmsPage(pageId,cmsPage);
        return cmsPageResult;
    }

    @Override
    @GetMapping("delete/{pageId}")
    public ResponseResult deleteByPageId(@PathVariable("pageId") String pageId) throws CustomerException {
        ResponseResult  responseResult =  cmsPageService.deleteByPageId(pageId);
        return responseResult;
    }
}
