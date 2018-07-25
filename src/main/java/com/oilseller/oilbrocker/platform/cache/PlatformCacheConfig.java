package com.oilseller.oilbrocker.platform.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableCaching
@EnableScheduling
public class PlatformCacheConfig {

    static final String ACCESS_TOKEN_CACHE = "access-token-cache";
    public static final String USERNAME_CACHE = "username";
    private static final Logger LOGGER = LoggerFactory.getLogger(PlatformCacheConfig.class);
    private static final long FIVE_MINUTES_IN_MILLIS = 5L * 60 * 1000;
    private static final long INITIAL_DELAY_MILLIS = 1000L;

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        List<Cache> caches = new ArrayList<>();
        caches.add(new ConcurrentMapCache(USERNAME_CACHE));
        caches.add(new ConcurrentMapCache(ACCESS_TOKEN_CACHE));
        cacheManager.setCaches(caches);
        return cacheManager;
    }
}
