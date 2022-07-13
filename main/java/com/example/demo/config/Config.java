package com.example.demo.config;


import com.example.demo.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
// 跨域访问
public class Config implements WebMvcConfigurer {


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 添加映射路径
        registry.addMapping("/**")
                // 放行哪些原始域
//                 .allwedOrigins("*")  // 2.2 之前的版本用的
//                .allowedOriginPatterns("*")
                .allowedOrigins("*")
                // 是否发送 Cookie 信息
                .allowCredentials(true)
                // 放行哪些原始域（请求方式）
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                // 放行哪些头部信息
                .allowedHeaders("*")
                // 暴露哪些头部信息（因为跨域访问默认不能获取全部头部信息）
                .exposedHeaders("Header1", "Header2");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        添加拦截器
//        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/user/**")
//                //放行路径，可以添加多个
//                .excludePathPatterns("/user/login").excludePathPatterns("/user/register");
//
//        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/book/**")
//                .excludePathPatterns("/book/findById").excludePathPatterns("/book/list")
//                .excludePathPatterns("/book/findByAuthorId").excludePathPatterns("/findByName")
//                .excludePathPatterns("/book/findByTypeId");
//
//        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/author/**")
//                .excludePathPatterns("/author/findById");
//
//        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/type/**")
//                .excludePathPatterns("/type/findAll").excludePathPatterns("/type/findById");
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)  {
//        registry.addResourceHandler("/images/**").addResourceLocations("/Users/rqjiang/Desktop/novel/static/images/book_cover/");

    }

}

