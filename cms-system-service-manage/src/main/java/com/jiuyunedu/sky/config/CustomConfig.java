package com.jiuyunedu.sky.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.templateresolver.StringTemplateResolver;

@Configuration
public class CustomConfig {

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
    }

    @Bean
    public StringTemplateResolver defaultTemplateResolver() {
        return new StringTemplateResolver();
    }
}
