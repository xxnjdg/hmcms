package com.xxnjdg.heimacms.managecourseservice.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author djr
 * @version 1.0
 * @date 2020/3/16 16:29
 */
@Configuration
public class RestTemplateConfig {
    /**
     * @param builder 自动注入 RestTemplateBuilder
     * @return 返回 RestTemplate
     */
    @Bean
    public RestTemplate getRestTemplate(RestTemplateBuilder builder){
        return builder.build();
    }
}
