package com.oilseller.oilbrocker.platform.scheduler;

import com.oilseller.oilbrocker.platform.cache.PlatformCacheConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static com.oilseller.oilbrocker.platform.scheduler.ScheduleTime.FIVE_MINUETS;
import static com.oilseller.oilbrocker.platform.scheduler.ScheduleTime.ONE_MINUET;

@Component
public class CacheScheduler {
    
    private static final Logger log = LoggerFactory.getLogger(CacheScheduler.class);
    
    @CacheEvict(PlatformCacheConfig.USERNAME_CACHE)
    @Scheduled(fixedDelay = FIVE_MINUETS, initialDelay = ONE_MINUET)
    public void clearCache() {
        log.info("Clearing the cache");
    }
    
    
}
