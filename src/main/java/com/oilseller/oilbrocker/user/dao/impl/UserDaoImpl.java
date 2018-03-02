package com.oilseller.oilbrocker.user.dao.impl;

import com.oilseller.oilbrocker.platform.dao.AbstractHibernateDao;
import com.oilseller.oilbrocker.user.dao.UserDao;
import com.oilseller.oilbrocker.user.entity.UserModel;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDaoImpl extends AbstractHibernateDao implements UserDao {

    @Override
    public Long addUser(UserModel userModel) {
        getSession().save(userModel);
        return userModel.getId();
    }

    @Override
    public UserModel getUserById(Long userId) {
        return getSession().get(UserModel.class, userId);
    }

    @Override
    public UserModel getUserByUsername(String username) {
        String hql = "from UserModel um where um.username=:username";
        Query query = getSession().createQuery(hql);
        query.setParameter("username", username);
        return (UserModel) query.uniqueResult();
    }
}
