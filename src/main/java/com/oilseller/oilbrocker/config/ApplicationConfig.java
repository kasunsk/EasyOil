package com.oilseller.oilbrocker.config;

import com.oilseller.oilbrocker.platform.interceptor.RequestValidationInterceptor;
import com.oilseller.oilbrocker.platform.interceptor.UserInterceptor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableTransactionManagement
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

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        List<Cache> caches = new ArrayList<>();
        caches.add(new ConcurrentMapCache("userName"));
        cacheManager.setCaches(caches);
        return cacheManager;
    }
}
