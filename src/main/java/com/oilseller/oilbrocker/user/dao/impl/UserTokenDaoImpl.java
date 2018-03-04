package com.oilseller.oilbrocker.user.dao.impl;

import com.oilseller.oilbrocker.platform.dao.AbstractHibernateDao;
import com.oilseller.oilbrocker.user.dao.UserTokenDao;
import com.oilseller.oilbrocker.user.dto.TokenStatus;
import com.oilseller.oilbrocker.user.entity.UserTokenModel;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

//@Repository("userTokenDao")
public class UserTokenDaoImpl {//extends AbstractHibernateDao implements UserTokenDao {


//    @Override
//    public String addToken(UserTokenModel userTokenModel) {
//        getSession().save(userTokenModel);
//        return userTokenModel.getUserToken();
//    }
//
//    @Override
//    public Boolean isValidToken(String userToken) {
//
//        String hql = "from UserTokenModel ut where ut.userToken =:userToken";
//        Query query = getSession().createQuery(hql);
//        query.setParameter("userToken", userToken);
//        UserTokenModel userTokenModel = (UserTokenModel) query.uniqueResult();
//
//        if (userTokenModel == null || userTokenModel.getTokenStatus().equals(TokenStatus.INVALID)){
//            return Boolean.FALSE;
//        }
//
//        Long numberOfRequests = userTokenModel.getNumOfRequest();
//        userTokenModel.setNumOfRequest(numberOfRequests + 1);
//        getSession().update(userTokenModel);
//        return Boolean.TRUE;
//    }
//
//    @Override
//    public UserTokenModel findByUserToken(String userToken) {
//
//        String hql = "from UserTokenModel ut where ut.userToken =:userToken";
//        Query query = getSession().createQuery(hql);
//        query.setParameter("userToken", userToken);
//        return (UserTokenModel) query.uniqueResult();
//    }
}
