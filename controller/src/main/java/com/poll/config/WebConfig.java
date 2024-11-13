package com.poll.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    // Specify the physical path of the external folder
    // "/www/wwwroot/static" for the server
//    public static final String physicalPath = "C:/Users/DELL/Desktop/static/";
    public static final String physicalPath = "view/static/";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Map all requests to the specified physical path
        registry.addResourceHandler("/static/**")
                .addResourceLocations("file:" + physicalPath);
    }
}