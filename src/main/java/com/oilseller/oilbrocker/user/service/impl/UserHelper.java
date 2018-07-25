package com.oilseller.oilbrocker.user.service.impl;

import com.oilseller.oilbrocker.user.dao.UserTokenDao;
import com.oilseller.oilbrocker.user.entity.UserTokenModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import static com.oilseller.oilbrocker.platform.cache.PlatformCacheConfig.USERNAME_CACHE;

@Component
public class UserHelper {

    private UserTokenDao userTokenDao;

    @Autowired
    public UserHelper(UserTokenDao userTokenDao) {
        this.userTokenDao = userTokenDao;
    }

    @Cacheable(value = USERNAME_CACHE)
    public UserTokenModel getByUserToken(String accessToken) {
        return userTokenDao.findByUserToken(accessToken);
    }
}
