package com.sc.mmusab.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.io.IOException;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/**")
        .addResourceLocations("classpath:/static/")
        .resourceChain(true)
        .addResolver(new PathResourceResolver() {
          @Override
          protected Resource getResource(String resourcePath,
                                         Resource location) throws IOException {
            Resource requestedResource = location.createRelative(resourcePath);
            return requestedResource.exists() && requestedResource.isReadable() ? requestedResource
                : new ClassPathResource("/static/index.html");
          }
        });
  }

  /*
  // Configured in SecurityConfig.java
  public void addCorsMappings(CorsRegistry registry) {
    registry
        .addMapping("/api/**")
        .allowedOrigins("http://localhost:3000/")
        .allowedMethods("*")
        .allowedHeaders("*")
        .allowCredentials(true)
        .exposedHeaders("*")
    ;
  }
   */
}
