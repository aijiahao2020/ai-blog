package com.aijiahao.blog.admin.modules.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.awt.geom.RectangularShape;

/**
 * @author aijiahao
 * @create 2023/8/23  16:03
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    // @Override
    // public void addCorsMappings(CorsRegistry registry) {
    //     registry.addMapping("/**")
    //             // .allowedOriginPatterns("*")
    //             .allowedOrigins("*")
    //             .allowCredentials(true)
    //             .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
    //             .maxAge(3600);
    // }
}
