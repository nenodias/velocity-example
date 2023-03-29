# velocity-example
Velocity example with springboot 2.7.10

### Motivações
A criação desse repositório foi para manter atualizável as aplicações com ***springboot*** que utilizam o ***Velocity*** como ***Template Engine***, 
pois o mesmo foi descontinuado pela pivotal.

### Dependências
as dependencias utilizadas foram:

- org.springframework.boot:spring-boot-starter-web:2.7.10
- org.apache.velocity:velocity-engine-core:2.3
- org.apache.velocity.tools:velocity-tools-generic:3.1
- org.apache.velocity.tools:velocity-tools-view:3.1

### Configurações
Para utilizar o mesmo foi necessário criar uma classe de configuração

```VelocityConfiguration.java
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
        viewResolver.setCache(true);
        viewResolver.setPrefix("/templates/");
        viewResolver.setSuffix(".vm");
        viewResolver.setContentType("text/html;charset=UTF-8");
        return viewResolver;
    }

}
```

E a criação de uma ***ViewResolver***, a classe ***SpringVelocityViewResolver***
