package com.xuecheng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
@EntityScan("com.xuecheng.framework.domain.cms")
@ComponentScan("com.xuecheng.api")
@ComponentScan("com.xuecheng.manage_cms")
@SpringBootApplication
public class MangeCmsSpringApplication {
    public static void main(String[] args) {
        SpringApplication.run(MangeCmsSpringApplication.class,args);
    }
}
