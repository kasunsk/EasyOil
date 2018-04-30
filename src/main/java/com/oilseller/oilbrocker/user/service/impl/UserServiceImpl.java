package com.oilseller.oilbrocker.user.service.impl;

import com.oilseller.oilbrocker.platform.exception.dto.ErrorCode;
import com.oilseller.oilbrocker.platform.exception.dto.ServiceRuntimeException;
import com.oilseller.oilbrocker.platform.thread.ThreadLocalContext;
import com.oilseller.oilbrocker.user.adaptor.UserModelAdaptor;
import com.oilseller.oilbrocker.user.dao.UserDao;
import com.oilseller.oilbrocker.user.dao.UserTokenDao;
import com.oilseller.oilbrocker.user.dto.User;
import com.oilseller.oilbrocker.user.dto.UserRoleType;
import com.oilseller.oilbrocker.user.entity.UserModel;
import com.oilseller.oilbrocker.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserDao userDao;
    private UserTokenDao tokenDao;

    private UserModelAdaptor userModelAdaptor = new UserModelAdaptor();

    @Autowired
    public UserServiceImpl(UserDao userDao, UserTokenDao tokenDao) {
        this.userDao = userDao;
        this.tokenDao = tokenDao;
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

    @Transactional
    @Override
    public Boolean removeUser(Long userId) {
        validateUserRemovePermision(userId);
        userDao.delete(userId);
        tokenDao.deleteAllByUserId(userId);
        return Boolean.TRUE;
    }

    private void validateUserRemovePermision(Long userId) {
        UserModel currentUser = userDao.findOne(userId);

        if (currentUser == null) {
            log.error("User not found for id {} ", userId);
            throw new ServiceRuntimeException(ErrorCode.NOT_FOUND, "No user found");
        }

        UserModel loggedUser = userDao.findByUsername(ThreadLocalContext.getUser());

        if (!loggedUser.getUserRole().equals(UserRoleType.ADMIN)) {
            log.error("User {} doesn't have privilege to remove another user", ThreadLocalContext.getUser());
            throw new ServiceRuntimeException(ErrorCode.PRIVILEGE_ERROR, "No privilege for user");
        }

        if (loggedUser.getId().equals(userId)) {
            log.error("User can't remove him self : {}", ThreadLocalContext.getUser());
            throw new ServiceRuntimeException(ErrorCode.PRIVILEGE_ERROR, "User can't remove him self");
        }
    }

    @Override
    public void invalidLogins() {
        
        
    }
}
