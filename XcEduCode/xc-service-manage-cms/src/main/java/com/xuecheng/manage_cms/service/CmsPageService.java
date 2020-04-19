package com.xuecheng.manage_cms.service;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsCode;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.framework.model.response.ResultCode;
import com.xuecheng.manage_cms.dao.CmsPageRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CmsPageService {
    @Autowired
    public CmsPageRepository cmsPageRepository;
    public QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest){
        if(page <=0){
            page=1;
        }
        if(page >=1 ){
            page--;
        }
        if(size<=0){
            size=20;
        }
        if(queryPageRequest==null){
            queryPageRequest = new QueryPageRequest();
        }
        PageRequest pageRequest = new PageRequest(page,size);
        CmsPage cmsPage = new CmsPage();
        if(StringUtils.isNoneBlank(queryPageRequest.getPageAliase())){
            cmsPage.setPageAliase(queryPageRequest.getPageAliase());
        }
        if(StringUtils.isNoneBlank(queryPageRequest.getSiteId())){
            cmsPage.setSiteId(queryPageRequest.getSiteId());
        }
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("pageAliase", ExampleMatcher.GenericPropertyMatchers.contains());
        Example<CmsPage> example  = Example.of(cmsPage,exampleMatcher);
        Page<CmsPage> cmsPages = cmsPageRepository.findAll(example,pageRequest);
//        Page<CmsPage> cmsPages = cmsPageRepository.findAll(pageRequest);
        QueryResult<CmsPage> queryResult = new QueryResult<>();
        queryResult.setList(cmsPages.getContent());
        queryResult.setTotal(cmsPages.getTotalElements());
        return  new QueryResponseResult(CommonCode.SUCCESS,queryResult);
    }
    @Transactional
    public CmsPageResult addCmsPage(CmsPage cmsPage) {
        CmsPage CmsPageVerification = cmsPageRepository.findByPageNameAndSiteIdAndPageWebPath(cmsPage.getPageName(),cmsPage.getSiteId(),cmsPage.getPageWebPath());
        if(CmsPageVerification !=null){
            return new CmsPageResult(CmsCode.CMS_ADDPAGE_EXISTSNAME,cmsPage);
        }
        //PageId应该由mongdb自动生成
        cmsPage.setPageId(null);
        CmsPage savedCmsPage = cmsPageRepository.save(cmsPage);
        return   new CmsPageResult(CommonCode.SUCCESS,savedCmsPage);
    }
}
