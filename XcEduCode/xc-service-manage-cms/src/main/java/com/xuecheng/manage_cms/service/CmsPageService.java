package com.xuecheng.manage_cms.service;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsCode;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.exception.CustomerException;
import com.xuecheng.framework.exception.ExceptionCast;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.manage_cms.dao.CmsPageRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
        if(StringUtils.isNoneBlank(queryPageRequest.getPageName())){
            cmsPage.setPageName(queryPageRequest.getPageName());
        }
        if(StringUtils.isNoneBlank(queryPageRequest.getPageType())){
            cmsPage.setPageType(queryPageRequest.getPageType());
        }
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("pageAliase", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("pageName",ExampleMatcher.GenericPropertyMatchers.contains());
        Example<CmsPage> example  = Example.of(cmsPage,exampleMatcher);
        Page<CmsPage> cmsPages = cmsPageRepository.findAll(example,pageRequest);
//        Page<CmsPage> cmsPages = cmsPageRepository.findAll(pageRequest);
        QueryResult<CmsPage> queryResult = new QueryResult<>();
        queryResult.setList(cmsPages.getContent());
        queryResult.setTotal(cmsPages.getTotalElements());
        return  new QueryResponseResult(CommonCode.SUCCESS,queryResult);
    }
    @Transactional
    public CmsPageResult addCmsPage(CmsPage cmsPage) throws CustomerException {
        CmsPage CmsPageVerification = cmsPageRepository.findByPageNameAndSiteIdAndPageWebPath(cmsPage.getPageName(),cmsPage.getSiteId(),cmsPage.getPageWebPath());
        if(CmsPageVerification !=null){
            ExceptionCast.cast(CmsCode.CMS_ADDPAGE_EXISTSNAME);
        }
        //PageId应该由mongdb自动生成
        cmsPage.setPageId(null);
        CmsPage savedCmsPage = cmsPageRepository.save(cmsPage);
        return   new CmsPageResult(CommonCode.SUCCESS,savedCmsPage);
    }

    public CmsPage findByPageId(String pageId) {
        Optional<CmsPage> OptionalcmsPage = cmsPageRepository.findById(pageId);
        if(OptionalcmsPage.isPresent()){
            return   OptionalcmsPage.get();

        }
        return  null;

    }
    @Transactional
    public CmsPageResult editCmsPage(String pageId, CmsPage cmsPage) throws CustomerException {
        if(cmsPage==null){
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        CmsPage one = findByPageId(pageId);
        if(one!=null){
            one.setSiteId(cmsPage.getSiteId());
            one.setTemplateId(cmsPage.getTemplateId());
            one.setPageName(cmsPage.getPageName());
            one.setPageAliase(cmsPage.getPageAliase());
            one.setPageWebPath(cmsPage.getPageWebPath());
            one.setPagePhysicalPath(cmsPage.getPagePhysicalPath());
            one.setDataUrl(cmsPage.getDataUrl());
            CmsPage save = cmsPageRepository.save(one);
            if(save!=null){
                return  new CmsPageResult(CommonCode.SUCCESS,save);
            }

        }
        return  new CmsPageResult(CommonCode.FAIL,null);
    }

    public ResponseResult deleteByPageId(String pageId) throws CustomerException {
        Optional<CmsPage> OptionalcmsPage = cmsPageRepository.findById(pageId);
        if(OptionalcmsPage.isPresent()){
            cmsPageRepository.deleteById(pageId);
            return  new ResponseResult(CommonCode.SUCCESS);
        }
        ExceptionCast.cast(CommonCode.FAIL);
        return  new ResponseResult(CommonCode.FAIL);
    }
}
