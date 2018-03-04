package com.oilseller.oilbrocker.user.dao;

import com.oilseller.oilbrocker.user.entity.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public interface UserDao extends JpaRepository<UserModel, Long> {

//    Long addUser(UserModel userModel);

//    UserModel getUserById(Long userId);

    UserModel findByUsername(String username);

//    List<UserModel> getAllUsers();
}
