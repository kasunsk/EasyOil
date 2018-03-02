package com.oilseller.oilbrocker.user.service;

import com.oilseller.oilbrocker.user.dto.LoginResponse;
import com.oilseller.oilbrocker.user.dto.User;

public interface AuthService {

    Boolean validateUserToken(String userToken);

    LoginResponse login(String username, String password);

    Boolean logout();
}
