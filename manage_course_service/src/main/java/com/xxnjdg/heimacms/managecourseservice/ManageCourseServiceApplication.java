package com.xxnjdg.heimacms.managecourseservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author djr
 * @version 1.0
 * @date 2020/3/13 17:23
 */
@SpringBootApplication
@ComponentScan("com.xxnjdg.heimacms.serviceapi")
@ComponentScan("com.xxnjdg.heimacms.managecourseservice")
@ComponentScan("com.xxnjdg.heimacms.common.exception")
public class ManageCourseServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManageCourseServiceApplication.class);
    }
}
