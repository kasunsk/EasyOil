package com.oilseller.oilbrocker.user.dao;

import com.oilseller.oilbrocker.user.entity.UserModel;

public interface UserDao {

    Long addUser(UserModel userModel);

    UserModel getUserById(Long userId);

    UserModel getUserByUsername(String username);
}
