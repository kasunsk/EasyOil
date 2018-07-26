package com.oilseller.oilbrocker.config;

import com.oilseller.oilbrocker.platform.interceptor.RequestValidationInterceptor;
import com.oilseller.oilbrocker.platform.interceptor.UserInterceptor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableTransactionManagement
@EnableScheduling
@EnableCaching
@EnableJpaRepositories(basePackages = {"com.oilseller.oilbrocker.product.dao", "com.oilseller.oilbrocker.user.dao", "com.oilseller.oilbrocker.order.dao", "com.oilseller.oilbrocker.history.dao"})
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

//    @Bean
//    public CacheManager cacheManager() {
//        SimpleCacheManager cacheManager = new SimpleCacheManager();
//        List<Cache> caches = new ArrayList<>();
//        caches.add(new ConcurrentMapCache("userName"));
//        cacheManager.setCaches(caches);
//        return cacheManager;
//    }
}
