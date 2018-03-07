package com.oilseller.oilbrocker.user.dao;

import com.oilseller.oilbrocker.user.entity.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public interface UserDao extends JpaRepository<UserModel, Long> {

    UserModel findByUsername(String username);
}
