package com.oilseller.oilbrocker.platform.scheduler;

import com.oilseller.oilbrocker.user.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class UserLoginInvalidScheduler {

    private static final Logger log = LoggerFactory.getLogger(UserLoginInvalidScheduler.class);
    
    private TokenService tokenService;

    @Autowired
    public UserLoginInvalidScheduler(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    // per half an hone
    @Scheduled(fixedDelay = 1800000, initialDelay = 10000)
    public void invalidLogin() {
        log.info("Token invalid scheduler");
        tokenService.invalidLogin();
    }

    // Per 3 days
    @Scheduled(fixedDelay = 259200000, initialDelay = 20000)
    public void removeInvalidLogin() {
        log.info("Token removing scheduler");
        tokenService.removeInvalidToken();
    }
}
