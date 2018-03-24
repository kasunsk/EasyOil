package com.oilseller.oilbrocker.user.service;

public interface TokenService {

    String addUserToken(String username);

    Boolean isValidRequest(String userToken);

    void invalidToken();

    String getUsername(String userToken);

    void invalidLogin();

    void removeInvalidToken();

}
