package com.oilseller.oilbrocker.user.dao;

import com.oilseller.oilbrocker.user.entity.UserTokenModel;

public interface UserTokenDao {

    String addToken(UserTokenModel userTokenModel);

    Boolean isValidToken(String userToken);

}
