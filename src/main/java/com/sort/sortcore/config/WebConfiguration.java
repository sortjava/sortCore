package com.sort.sortcore.config;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    @Value("${sort.okhttp.maxIdleConnections}")
    private int maxIdleConnections;
    @Value("${sort.okhttp.keepAliveDuration}")
    private long keepAliveDuration;
    @Value("${sort.okhttp.callTimeout}")
    private long callTimeout;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/static/home");
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }

    @Bean
    public OkHttpClient httpClient() {
        return (new Builder()).retryOnConnectionFailure(true)
                .connectionPool(new ConnectionPool(this.maxIdleConnections, this.keepAliveDuration, TimeUnit.SECONDS))
                .callTimeout(this.callTimeout, TimeUnit.SECONDS).build();
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(
                "api/**",
                "css/**",
                "img/**",
                "js/**"
        ).addResourceLocations(
                "classpath:/static/api/",
                "classpath:/static/css/",
                "classpath:/static/img/",
                "classpath:/static/js/"
        );
    }
}