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
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

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

    @Transactional
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
        String userToken = generateUserToken(user);
        loginResponse.setToken(userToken);
        return loginResponse;
    }

    private String generateUserToken(User user) {
        String partOne = user.getUsername();
        String partTwo = new Date().getTime() + "";
        String partThree = user.getCreatedDate().getTime() + "";
        return partOne + partTwo + partThree;
    }

    @Transactional
    @Override
    public Boolean logout() {
        return null;
    }
}
