package com.exam.orm.config;

import com.exam.orm.service.RateLimiterInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class RateLimiterConfig implements WebMvcConfigurer {
    private final RateLimiterInterceptor rateLimiterConfig;
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
    @Autowired
    public RateLimiterConfig(RateLimiterInterceptor rateLimiterConfig) {
        this.rateLimiterConfig = rateLimiterConfig;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor((HandlerInterceptor) rateLimiterConfig)
                    .addPathPatterns("/app/concurrently"); // Apply interceptor to endpoints under "/fetch" path

    }
}
