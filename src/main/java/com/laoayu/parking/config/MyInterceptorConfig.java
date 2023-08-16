package com.laoayu.parking.config;

import com.laoayu.parking.interceptor.JwtValidateInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: LaoAyu
 * @date: 2023/03/10
 **/

//注册拦截器
@Configuration
public class MyInterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private JwtValidateInterceptor jwtValidateInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(jwtValidateInterceptor);
        //定义拦截规则
        registration.addPathPatterns("/**")
                .excludePathPatterns(
                        "/user/login",
                        "/user/info",
                        "/user/logout",
                        "/error",
                        "/swagger-ui/**",
                        "/swagger-resources/**",
                        "/v3/**",
                        "/file/upLoad",//将文件上传接口放开
                        "/carScan/carScan",//将文件上传接口放开
                        "/pic/**",//文件访问路径放开
                        "/oauth/**"
                );
    }

    /**
     * 静态资源映射
     * 访问静态资源路径：http://localhost:9999/pic/1.jpg
     * 映射到本地磁盘上的：D:/ParkingManagementSystem/Parking/pic/1.jpg
     * 可以添加多个映射，多个磁盘位置都可用 http://localhost:9999/pic/** 访问到本地此磁盘文件
     */

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/pic/**")
                .addResourceLocations("file:D:/ParkingManagementSystem/Parking/car_pic/")
                .addResourceLocations("file:D:/ParkingManagementSystem/Parking/park_pic/");
    }
}
