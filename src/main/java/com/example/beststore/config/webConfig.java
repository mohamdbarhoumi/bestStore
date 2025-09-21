package com.example.beststore.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class webConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        System.out.println("🔧 WebConfig loaded! Setting up image serving...");

        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:uploads/images/");

        System.out.println("📁 Images will be served from: uploads/images/");
    }
}