package com.oilseller.oilbrocker.user.service.impl;

import com.oilseller.oilbrocker.platform.exception.ServiceRuntimeException;
import com.oilseller.oilbrocker.user.adaptor.UserModelAdaptor;
import com.oilseller.oilbrocker.user.dao.UserDao;
import com.oilseller.oilbrocker.user.dto.User;
import com.oilseller.oilbrocker.user.entity.UserModel;
import com.oilseller.oilbrocker.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    private UserModelAdaptor userModelAdaptor = new UserModelAdaptor();

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    @Override
    public Long addUser(User user) {

        UserModel userModel = userDao.getUserByUsername(user.getUsername());

        if (userModel.getUsername().equals(user.getUsername())) {
            throw new ServiceRuntimeException("ALREADY_EXIST","Username already exist");
        }
        return userDao.addUser(userModelAdaptor.fromDto(user));
    }

    @Transactional
    @Override
    public User getUserByUsername(String username) {
        return userModelAdaptor.fromModel(userDao.getUserByUsername(username));
    }

    @Transactional
    @Override
    public User loadUserById(Long userId) {
        return userModelAdaptor.fromModel(userDao.getUserById(userId));
    }

    @Transactional
    @Override
    public List<User> loadAllUsers() {
        return userModelAdaptor.fromModelList(userDao.getAllUsers());
    }
}
