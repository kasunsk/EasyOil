package com.oilseller.oilbrocker.user.service.impl;

import com.oilseller.oilbrocker.user.dao.UserDao;
import com.oilseller.oilbrocker.user.dao.UserTokenDao;
import com.oilseller.oilbrocker.user.dto.TokenStatus;
import com.oilseller.oilbrocker.user.entity.UserModel;
import com.oilseller.oilbrocker.user.entity.UserTokenModel;
import com.oilseller.oilbrocker.user.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service("tokenService")
public class TokenServiceImpl implements TokenService {

    private UserDao userDao;
    private UserTokenDao userTokenDao;

    @Autowired
    public TokenServiceImpl( UserDao userDao, UserTokenDao userTokenDao) {
        this.userTokenDao = userTokenDao;
        this.userDao = userDao;
    }

    @Transactional
    @Override
    public String addUserToken(String username) {
        UserModel user = userDao.getUserByUsername(username);
        UserTokenModel userTokenModel = new UserTokenModel();
        userTokenModel.setNumOfRequest(0L);
        userTokenModel.setTokenStatus(TokenStatus.VALID);
        userTokenModel.setUserId(user.getId());
        userTokenModel.setUsername(user.getUsername());
        String userToken = generateUserToken(user);
        userTokenModel.setUserToken(userToken);
        userTokenDao.addToken(userTokenModel);
        return userTokenModel.getUserToken();
    }

    @Transactional
    @Override
    public Boolean isValidRequest(String userToken) {
        return userTokenDao.isValidToken(userToken);
    }

    @Transactional
    @Override
    public String getUsername(String userToken) {

        UserTokenModel tokenModel = userTokenDao.getUserTokenEntity(userToken);
        return tokenModel.getUsername();
    }

    private String generateUserToken(UserModel user) {

        String partOne = user.getUsername();
        String partTwo = new Date().getTime() + "";
        return partOne + partTwo;
    }
}
