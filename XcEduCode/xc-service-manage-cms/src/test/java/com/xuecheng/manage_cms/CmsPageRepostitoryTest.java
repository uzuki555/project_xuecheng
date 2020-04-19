package com.xuecheng.manage_cms;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.manage_cms.dao.CmsPageRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.yaml.snakeyaml.introspector.GenericProperty;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CmsPageRepostitoryTest {
    @Autowired
    public CmsPageRepository cmsPageRepository;

    @Test
    public void findById(){
        Optional<CmsPage> cmsPage = cmsPageRepository.findById("5a795ac7dd573c04508f3a56");
        System.out.println(cmsPage.get());
    }
    @Test
    public  void  findAll(){
        List<CmsPage> all = cmsPageRepository.findAll();
        for (CmsPage cmsPage : all) {
            System.out.println(cmsPage);
        }
    }
    @Test
    public  void  findByPageable(){
        PageRequest pageRequest = new PageRequest(2,3);

        Page<CmsPage> all = cmsPageRepository.findAll(pageRequest);
        all.getContent().forEach(cmsPage -> {
            System.out.println(cmsPage);
        });

    }
    @Test
    public  void InsetNewElement(){
        CmsPage cmsPage = new CmsPage();
        cmsPage.setPageName("测试");
        cmsPage.setPageAliase("测试");
        cmsPage.setPageHtml("测试");
        cmsPage.setSiteId("测试");
        CmsPage insert = cmsPageRepository.insert(cmsPage);
        System.out.println(insert);
    }
    @Test
    public  void  UpdateTestRecord(){
        Optional<CmsPage> cmsPage = cmsPageRepository.findById("5e8411f0e69411482cfd5329");
        if(cmsPage.isPresent()){
            CmsPage cmsPage1 = cmsPage.get();
            cmsPage1.setPageName("测试更新");
            cmsPageRepository.save(cmsPage1);
        }
    }
    @Test
    public  void  DeleteTestRecord(){
        CmsPage cmsPage = new CmsPage();
        cmsPage.setPageName("测试");
        cmsPage.setPageAliase("测试");
        cmsPage.setPageHtml("测试");
        cmsPage.setSiteId("测试");
        cmsPage.setPageId("5e84141ee694112a58086f45");
        cmsPageRepository.delete(cmsPage);
    }
    @Test
    public  void  DeleteTestRecordById(){

        cmsPageRepository.deleteById("5e8411f0e69411482cfd5329");
    }
    @Test
    public void  findBySiteIdAndPageAliase(){
        CmsPage cmsPage = new CmsPage();
        cmsPage.setSiteId("5a751fab6abb5044e0d19ea1");
        cmsPage.setPageAliase("课程");
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("pageAliase", ExampleMatcher.GenericPropertyMatchers.contains());
        Example<CmsPage> example  = Example.of(cmsPage,exampleMatcher);
        List<CmsPage> list = cmsPageRepository.findAll(example);
        list.forEach(cmsPageChild ->{
            System.out.println(cmsPageChild);
        });
    }
}
