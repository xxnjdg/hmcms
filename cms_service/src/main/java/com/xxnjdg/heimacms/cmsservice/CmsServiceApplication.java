package com.xxnjdg.heimacms.cmsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author djr
 * @version 1.0
 * @date 2020/3/9 11:47
 */
@SpringBootApplication
@ComponentScan("com.xxnjdg.heimacms.serviceapi")
@ComponentScan("com.xxnjdg.heimacms.cmsservice")
@ComponentScan("com.xxnjdg.heimacms.servicemodel")
@ComponentScan("com.xxnjdg.heimacms.common")
public class CmsServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmsServiceApplication.class);
    }
}
