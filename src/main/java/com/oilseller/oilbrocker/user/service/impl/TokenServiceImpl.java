package com.oilseller.oilbrocker.user.service.impl;

import com.oilseller.oilbrocker.user.dao.UserDao;
import com.oilseller.oilbrocker.user.dao.UserTokenDao;
import com.oilseller.oilbrocker.user.dto.TokenStatus;
import com.oilseller.oilbrocker.user.entity.UserModel;
import com.oilseller.oilbrocker.user.entity.UserTokenModel;
import com.oilseller.oilbrocker.user.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service("tokenService")
public class TokenServiceImpl implements TokenService {

    private UserDao userDao;
    private UserTokenDao userTokenDao;

    private Environment environment;

    @Autowired
    public TokenServiceImpl( UserDao userDao, UserTokenDao userTokenDao, Environment environment) {
        this.userTokenDao = userTokenDao;
        this.userDao = userDao;
        this.environment = environment;
    }

    @Transactional
    @Override
    public String addUserToken(String username) {
        UserModel user = userDao.findByUsername(username);
        UserTokenModel userTokenModel = new UserTokenModel();
        userTokenModel.setNumOfRequest(0L);
        userTokenModel.setTokenStatus(TokenStatus.VALID);
        userTokenModel.setUserId(user.getId());
        userTokenModel.setUsername(user.getUsername());
        String userToken = generateUserToken(user);
        userTokenModel.setUserToken(userToken);
        userTokenDao.save(userTokenModel);
        return userTokenModel.getUserToken();
    }

    @Transactional
    @Override
    public Boolean isValidRequest(String userToken) {

        UserTokenModel tokenModel = userTokenDao.findByUserToken(userToken);

        if (tokenModel != null) {
            Long currentNumOfRequest = tokenModel.getNumOfRequest();
            if (tokenModel.getTokenStatus().equals(TokenStatus.VALID)) {
                tokenModel.setNumOfRequest(currentNumOfRequest + 1);
                userTokenDao.save(tokenModel);
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    @Transactional
    @Override
    public String getUsername(String userToken) {

        UserTokenModel tokenModel = userTokenDao.findByUserToken(userToken);
        return tokenModel.getUsername();
    }

    @Transactional
    @Override
    public void invalidLogin() {

        Date lastTokenValidTime = Date.from(new Date().toInstant().minusSeconds(Long.getLong(environment.getProperty("token.invalid.time"))));
        List<UserTokenModel> tokenModels = userTokenDao.findAllByTokenStatusAndLastModifiedDateLessThan(
                TokenStatus.VALID, lastTokenValidTime);

        if (tokenModels != null) {
            for (UserTokenModel tokenModel : tokenModels) {

                if (tokenModel.getLastModifiedDate() != null) {
                    tokenModel.setTokenStatus(TokenStatus.INVALID);
                } else if (tokenModel.getCreatedDate().compareTo(lastTokenValidTime) == 0) {
                    tokenModel.setTokenStatus(TokenStatus.INVALID);
                }
            }
        }
        userTokenDao.save(tokenModels);
    }

    @Transactional
    @Override
    public void removeInvalidToken() {

        Date lastTokenValidTime = Date.from(new Date().toInstant().minusSeconds(Long.getLong(environment.getProperty("token.remove.time"))));
        List<UserTokenModel> tokenModels = userTokenDao.findAllByTokenStatusAndLastModifiedDateLessThan(TokenStatus.INVALID, lastTokenValidTime);

        if (tokenModels != null) {
            for (UserTokenModel tokenModel : tokenModels) {
                if (tokenModel.getLastModifiedDate() == null && tokenModel.getCreatedDate().compareTo(lastTokenValidTime) == 0) {
                    tokenModels.remove(tokenModel);
                }
            }
            userTokenDao.delete(tokenModels);
        }
    }

    private String generateUserToken(UserModel user) {

        String partOne = user.getUsername();
        String partTwo = new Date().getTime() + "";
        return partOne + partTwo;
    }
}
