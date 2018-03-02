package com.oilseller.oilbrocker.user.service.impl;

import com.oilseller.oilbrocker.platform.exception.ServiceRuntimeException;
import com.oilseller.oilbrocker.platform.thread.ThreadLocalContext;
import com.oilseller.oilbrocker.user.adaptor.UserModelAdaptor;
import com.oilseller.oilbrocker.user.dao.UserDao;
import com.oilseller.oilbrocker.user.dto.LoginResponse;
import com.oilseller.oilbrocker.user.dto.User;
import com.oilseller.oilbrocker.user.entity.UserModel;
import com.oilseller.oilbrocker.user.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("authService")
public class AuthServiceImpl implements AuthService {

    private UserDao userDao;

    private UserModelAdaptor userModelAdaptor = new UserModelAdaptor();

    @Autowired
    public AuthServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Boolean validateUserToken(String userToken) {
        return Boolean.TRUE;
    }

    @Override
    public LoginResponse login(String username, String password) {

        UserModel currentUser = userDao.getUserByUsername(username);

        if (currentUser == null || !currentUser.getPassword().equals(password)) {
            throw new ServiceRuntimeException("INVALID_CREDENTIALS", "Invalid Credintials");
        }
        User user = userModelAdaptor.fromModel(currentUser);
        user.setPassword(null);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setUser(user);

        return loginResponse;
    }

    @Override
    public Boolean logout() {
        return null;
    }
}
