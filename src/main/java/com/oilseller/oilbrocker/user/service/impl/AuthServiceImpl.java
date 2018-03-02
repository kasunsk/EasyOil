package com.oilseller.oilbrocker.user.service.impl;

import com.oilseller.oilbrocker.user.service.AuthService;
import org.springframework.stereotype.Service;

@Service("authService")
public class AuthServiceImpl implements AuthService {

    @Override
    public Boolean validateUserToken(String userToken) {
        return Boolean.TRUE;
    }
}
