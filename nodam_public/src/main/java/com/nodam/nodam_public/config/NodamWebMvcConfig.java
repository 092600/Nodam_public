package com.nodam.nodam_public.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import org.springframework.beans.factory.annotation.Value;

@Configuration
public class NodamWebMvcConfig implements WebMvcConfigurer{

    @Value("${userProfileImgPath}")
    String userProfileImgPath;
    
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/userProfileImgPath/**")
                .addResourceLocations(userProfileImgPath);
    }

}
