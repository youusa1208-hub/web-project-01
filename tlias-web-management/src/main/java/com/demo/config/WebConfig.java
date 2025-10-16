package com.demo.config;

import com.demo.interceptor.DemoInterceptor;
import com.demo.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web配置类
 **/
@Configuration
public class WebConfig implements WebMvcConfigurer {

//    @Autowired
//    private DemoInterceptor demoInterceptor;

//    @Autowired
//    private TokenInterceptor tokenInterceptor;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new DemoInterceptor()).addPathPatterns("/**");
//        registry.addInterceptor(new TokenInterceptor())
//                .addPathPatterns("/**")// 拦截所有请求
//                .excludePathPatterns("/login");// 排除登录请求，登录请求不需要拦截
//    }
}
