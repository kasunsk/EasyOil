package com.oilseller.oilbrocker.user.service.impl;

import com.oilseller.oilbrocker.platform.exception.dto.ErrorCode;
import com.oilseller.oilbrocker.platform.exception.dto.ServiceRuntimeException;
import com.oilseller.oilbrocker.user.adaptor.UserModelAdaptor;
import com.oilseller.oilbrocker.user.dao.UserDao;
import com.oilseller.oilbrocker.user.dto.User;
import com.oilseller.oilbrocker.user.entity.UserModel;
import com.oilseller.oilbrocker.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
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

        UserModel userModel = userDao.findByUsername(user.getUsername());

        if (userModel != null) {
            throw new ServiceRuntimeException(ErrorCode.ALREADY_EXIST,"Username already exist");
        }
        return userDao.save(userModelAdaptor.fromDto(user)).getId();
    }

    @Transactional
    @Override
    public User getUserByUsername(String username) {
        return userModelAdaptor.fromModel(userDao.findByUsername(username));
    }

    @Transactional
    @Override
    public User loadUserById(Long userId) {
        return userModelAdaptor.fromModel(userDao.findOne(userId));
    }

    @Transactional
    @Override
    public List<User> loadAllUsers() {
        return userModelAdaptor.fromModelList((Collection<UserModel>) userDao.findAll());
    }
}
