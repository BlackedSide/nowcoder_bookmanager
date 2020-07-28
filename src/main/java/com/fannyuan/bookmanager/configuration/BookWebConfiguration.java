package com.fannyuan.bookmanager.configuration;

import com.fannyuan.bookmanager.interceptor.HostInfoInterceptor;
import com.fannyuan.bookmanager.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Component
public class BookWebConfiguration implements WebMvcConfigurer {

    @Resource
    private HostInfoInterceptor hostInfoInterceptor;

    @Resource
    private LoginInterceptor loginInterceptor;

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(hostInfoInterceptor).addPathPatterns("/**");
                registry.addInterceptor(loginInterceptor).addPathPatterns("/books/**");
            }
        };
    }
}
