package com.lonton.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebMVCConfig extends WebMvcConfigurationSupport{
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/public/**")
                .addResourceLocations("classpath:/public/");
		registry.addResourceHandler("/**")
				.addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

	}

}
