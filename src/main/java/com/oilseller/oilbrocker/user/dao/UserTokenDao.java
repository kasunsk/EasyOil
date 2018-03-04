package com.oilseller.oilbrocker.user.dao;

import com.oilseller.oilbrocker.user.entity.UserTokenModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userTokenDao")
public interface UserTokenDao extends JpaRepository<UserTokenModel, Long> {

//    String addToken(UserTokenModel userTokenModel);
//    @Query("select u from UserTokenModel utm where utm.userToken=:userToken")
//    Boolean isValidToken(String userToken);

//    @Query(value = "select u from UserTokenModel utm where utm.userToken=:userToken")
    UserTokenModel findByUserToken(String userToken);

}
