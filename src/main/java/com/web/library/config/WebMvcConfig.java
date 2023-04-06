package com.web.library.config;

import com.web.library.interceptor.LoggerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoggerInterceptor()).excludePathPatterns("/css/**", "/images/**", "/js/**");
        // excludePathPatterns : 지정된 경로나 URI는 인터셉터 호출에서 제외 (무시)
    }
}
