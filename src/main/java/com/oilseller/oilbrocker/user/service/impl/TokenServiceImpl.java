package com.oilseller.oilbrocker.user.service.impl;

import com.oilseller.oilbrocker.platform.exception.dto.ErrorCode;
import com.oilseller.oilbrocker.platform.exception.dto.ServiceRuntimeException;
import com.oilseller.oilbrocker.platform.thread.ThreadLocalContext;
import com.oilseller.oilbrocker.user.dao.UserDao;
import com.oilseller.oilbrocker.user.dao.UserTokenDao;
import com.oilseller.oilbrocker.user.dto.TokenStatus;
import com.oilseller.oilbrocker.user.entity.UserModel;
import com.oilseller.oilbrocker.user.entity.UserTokenModel;
import com.oilseller.oilbrocker.user.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static com.oilseller.oilbrocker.platform.cache.PlatformCacheConfig.USERNAME_CACHE;

@Service("tokenService")
public class TokenServiceImpl implements TokenService {

    private static final Logger log = LoggerFactory.getLogger(TokenServiceImpl.class);

    private UserDao userDao;
    private UserTokenDao userTokenDao;

    private Environment environment;

    @Autowired
    public TokenServiceImpl(UserDao userDao, UserTokenDao userTokenDao, Environment environment) {
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
    public void invalidToken() {
        String accessToken = ThreadLocalContext.getAccessToken();
        UserTokenModel userToken = userTokenDao.findByUserToken(accessToken);
        userToken.setTokenStatus(TokenStatus.INVALID);
        userTokenDao.save(userToken);
    }

    @Cacheable(value = USERNAME_CACHE)
    @Transactional
    @Override
    public String getUsername(String userToken) {
        UserTokenModel tokenModel = userTokenDao.findByUserToken(userToken);

        if (tokenModel == null) {
            throw new ServiceRuntimeException(ErrorCode.NOT_FOUND, "Token not found");
        }
        return tokenModel.getUsername();
    }

    @Transactional
    @Override
    public void invalidLogin() {

        Date lastTokenValidTime = Date.from(new Date().toInstant().minusSeconds(Long.parseLong(environment.getProperty("token.invalid.time"))));
        List<UserTokenModel> tokenModels = userTokenDao.findAllByTokenStatusAndLastModifiedDateLessThanOrLastModifiedDateIsNull(
                TokenStatus.VALID, lastTokenValidTime);

        if (tokenModels != null) {
            for (UserTokenModel tokenModel : tokenModels) {

                if (tokenModel.getLastModifiedDate() != null) {
                    tokenModel.setTokenStatus(TokenStatus.INVALID);
                } else if (lastTokenValidTime.compareTo(tokenModel.getCreatedDate()) == 1) {
                    tokenModel.setTokenStatus(TokenStatus.INVALID);
                }
            }
        }
        userTokenDao.save(tokenModels);
    }

    @Transactional
    @Override
    public void removeInvalidToken() {

        Date lastTokenValidTime = Date.from(new Date().toInstant().minusSeconds(Long.parseLong(environment.getProperty("token.remove.time"))));
        List<UserTokenModel> tokenModels = userTokenDao.findAllByTokenStatusAndLastModifiedDateLessThanOrLastModifiedDateIsNull(TokenStatus.INVALID, lastTokenValidTime);

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
