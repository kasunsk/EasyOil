package com.oilseller.oilbrocker.config;

import com.oilseller.oilbrocker.platform.interceptor.RequestValidationInterceptor;
import com.oilseller.oilbrocker.platform.interceptor.UserInterceptor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class ApplicationConfig  extends WebMvcConfigurerAdapter {

    private RequestValidationInterceptor requestValidationInterceptor;
    private UserInterceptor userInterceptor;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Autowired
    public ApplicationConfig(RequestValidationInterceptor requestValidationInterceptor, UserInterceptor userInterceptor) {
        this.requestValidationInterceptor = requestValidationInterceptor;
        this.userInterceptor = userInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestValidationInterceptor);
        registry.addInterceptor(userInterceptor);
    }
}
