package com.oilseller.oilbrocker.user.dao;

import com.oilseller.oilbrocker.user.dto.TokenStatus;
import com.oilseller.oilbrocker.user.entity.UserTokenModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository("userTokenDao")
public interface UserTokenDao extends JpaRepository<UserTokenModel, Long> {

    UserTokenModel findByUserToken(String userToken);

    List<UserTokenModel> findAllByTokenStatusAndLastModifiedDateLessThan(TokenStatus tokenStatus,Date lastModifiedDate);


    @Modifying
    @Query(value = "update UserTokenModel utm set utm.tokenStatus= ?1 where utm.lastModifiedDate < ?2")
    void invalidLogin(@Param("tokenStatus") TokenStatus tokenStatus, @Param("date") Date date);

    void deleteAllByTokenStatusAndLastModifiedDateLessThan(TokenStatus tokenStatus,Date date);

}
