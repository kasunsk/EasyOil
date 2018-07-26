package com.oilseller.oilbrocker.platform.scheduler;

import com.oilseller.oilbrocker.user.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static com.oilseller.oilbrocker.platform.scheduler.ScheduleTime.HALF_AN_OUR;
import static com.oilseller.oilbrocker.platform.scheduler.ScheduleTime.ONE_MINUET;
import static com.oilseller.oilbrocker.platform.scheduler.ScheduleTime.THREE_DAYS;


@Component
public class UserLoginInvalidScheduler {

    private static final Logger log = LoggerFactory.getLogger(UserLoginInvalidScheduler.class);

    private TokenService tokenService;

    @Autowired
    public UserLoginInvalidScheduler(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Scheduled(fixedDelay = HALF_AN_OUR, initialDelay = ONE_MINUET)
    public void invalidLogin() {
        log.info("Token invalid scheduler");
        tokenService.invalidLogin();
    }

    @Scheduled(fixedDelay = THREE_DAYS, initialDelay = ONE_MINUET)
    public void removeInvalidLogin() {
        log.info("Token removing scheduler");
        tokenService.removeInvalidToken();
    }
}
