package com.oilseller.oilbrocker.user.service;

import com.oilseller.oilbrocker.user.dto.User;

import java.util.List;

public interface UserService {

    Long addUser(User user);

    User getUserByUsername(String username);

    User loadUserById(Long userId);

    List<User> loadAllUsers();

    Boolean removeUser(Long userId);

    void invalidLogins();
}
