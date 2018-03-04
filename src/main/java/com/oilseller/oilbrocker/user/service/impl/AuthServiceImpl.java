package com.oilseller.oilbrocker.user.service.impl;

import com.oilseller.oilbrocker.platform.exception.dto.ErrorCode;
import com.oilseller.oilbrocker.platform.exception.dto.ServiceRuntimeException;
import com.oilseller.oilbrocker.user.adaptor.UserModelAdaptor;
import com.oilseller.oilbrocker.user.dao.UserDao;
import com.oilseller.oilbrocker.user.dto.LoginResponse;
import com.oilseller.oilbrocker.user.dto.User;
import com.oilseller.oilbrocker.user.entity.UserModel;
import com.oilseller.oilbrocker.user.service.AuthService;
import com.oilseller.oilbrocker.user.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("authService")
public class AuthServiceImpl implements AuthService {

    private UserDao userDao;
    private TokenService tokenService;

    private UserModelAdaptor userModelAdaptor = new UserModelAdaptor();

    @Autowired
    public AuthServiceImpl(UserDao userDao, TokenService tokenService) {
        this.userDao = userDao;
        this.tokenService = tokenService;
    }

    @Override
    public Boolean validateUserToken(String userToken) {
        return Boolean.TRUE;
    }

    @Transactional
    @Override
    public LoginResponse login(String username, String password) {

        UserModel currentUser = userDao.findByUsername(username);

        if (currentUser == null || !currentUser.getPassword().equals(password)) {
            throw new ServiceRuntimeException(ErrorCode.AUTH_ERROR, "Invalid Credentials");
        }
        User user = userModelAdaptor.fromModel(currentUser);
        String userToken = tokenService.addUserToken(username);
        user.setPassword(null);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setUser(user);
        loginResponse.setToken(userToken);
        return loginResponse;
    }

    @Transactional
    @Override
    public Boolean logout() {
        return null;
    }
}
