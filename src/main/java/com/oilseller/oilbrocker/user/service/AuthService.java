package com.oilseller.oilbrocker.user.service;

import com.oilseller.oilbrocker.user.dto.LoginResponse;

public interface AuthService {

    Boolean validateUserToken(String userToken);

    LoginResponse login(String username, String password);

    Boolean logout();
}
