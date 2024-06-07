package com.github.danilogmoura.algafood.core.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedMethods("*");
//            .allowedOrigins("http://localhost:63342")
//            .allowedMethods("GET", "HEAD", "POST")
//            .maxAge(30);
    }
}
