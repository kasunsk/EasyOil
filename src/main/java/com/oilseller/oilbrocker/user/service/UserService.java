package com.oilseller.oilbrocker.user.service;

import com.oilseller.oilbrocker.user.dto.User;

public interface UserService {

    Long addUser(User user);

    User getUserByUsername(String username);

    User loadUserById(Long userId);
}
