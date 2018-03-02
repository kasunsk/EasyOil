package com.oilseller.oilbrocker.user.dao;

import com.oilseller.oilbrocker.user.entity.UserModel;

import java.util.List;

public interface UserDao {

    Long addUser(UserModel userModel);

    UserModel getUserById(Long userId);

    UserModel getUserByUsername(String username);

    List<UserModel> getAllUsers();
}
