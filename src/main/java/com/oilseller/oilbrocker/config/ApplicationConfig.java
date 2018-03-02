package com.oilseller.oilbrocker.config;

import com.oilseller.oilbrocker.platform.interceptor.RequestValidationInterceptor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class ApplicationConfig  extends WebMvcConfigurerAdapter {

    RequestValidationInterceptor requestValidationInterceptor;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Autowired
    public ApplicationConfig(RequestValidationInterceptor requestValidationInterceptor) {
        this.requestValidationInterceptor = requestValidationInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestValidationInterceptor);
    }
}
