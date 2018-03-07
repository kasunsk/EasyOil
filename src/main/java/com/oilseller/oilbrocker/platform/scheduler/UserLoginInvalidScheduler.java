package com.oilseller.oilbrocker.platform.scheduler;

import com.oilseller.oilbrocker.user.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class UserLoginInvalidScheduler {
    
    private TokenService tokenService;

    @Autowired
    public UserLoginInvalidScheduler(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    // per half an hone
    @Scheduled(fixedDelay = 1800000L, initialDelay = 1800000L)
    public void invalidLogin() {
        tokenService.invalidLogin();
    }

    // Per 15 days
    @Scheduled(fixedDelay = 1296000000L, initialDelay = 1296000000L)
    public void removeInvalidLogin() {
        tokenService.removeInvalidToken();
    }
}
