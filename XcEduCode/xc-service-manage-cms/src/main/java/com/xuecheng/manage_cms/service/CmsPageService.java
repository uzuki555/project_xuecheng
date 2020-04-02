package com.xuecheng.manage_cms.service;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.manage_cms.dao.CmsPageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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
        cmsPage.setSiteId(queryPageRequest.getSiteId());
        cmsPage.setPageName(queryPageRequest.getPageName());
        cmsPage.setPageAliase(queryPageRequest.getPageAliase());
        cmsPage.setTemplateId(queryPageRequest.getTemplateId());
        cmsPage.setPageId(queryPageRequest.getPageId());
        Example<CmsPage> example =Example.of(cmsPage);
        Page<CmsPage> cmsPages = cmsPageRepository.findAll(example,pageRequest);
//        Page<CmsPage> cmsPages = cmsPageRepository.findAll(pageRequest);
        QueryResult<CmsPage> queryResult = new QueryResult<>();
        queryResult.setList(cmsPages.getContent());
        queryResult.setTotal(cmsPages.getTotalElements());
        return  new QueryResponseResult(CommonCode.SUCCESS,queryResult);
    }
}
