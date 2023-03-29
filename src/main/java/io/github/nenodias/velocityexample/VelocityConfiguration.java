package io.github.nenodias.velocityexample;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.ServletConfig;

@Configuration
public class VelocityConfiguration implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    @Bean
    public VelocityEngine velocityEngine() {
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADERS, "class");
        velocityEngine.setProperty("resource.loader.class.class", ClasspathResourceLoader.class.getName());
        return velocityEngine;
    }

    @Bean
    public ViewResolver viewResolver(final VelocityEngine velocityEngine,final ServletConfig servletConfig) {
        SpringVelocityViewResolver viewResolver = new SpringVelocityViewResolver(velocityEngine, servletConfig);
        viewResolver.setPrefix("/templates/");
        viewResolver.setSuffix(".vm");
        viewResolver.setContentType("text/html;charset=UTF-8");
        return viewResolver;
    }

}
