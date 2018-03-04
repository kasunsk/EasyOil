package com.oilseller.oilbrocker.history.dao.impl;

import com.oilseller.oilbrocker.history.dao.HistoryDao;
import com.oilseller.oilbrocker.history.model.HistoryItemModel;
import com.oilseller.oilbrocker.platform.dao.AbstractHibernateDao;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public class HistoryDaoImpl {//extends AbstractHibernateDao implements HistoryDao {

//    @Override
//    public Long addHistoryItem(HistoryItemModel historyItemModel) {
//        getSession().save(historyItemModel);
//        return historyItemModel.getHistoryId();
//    }
//
//    @Override
//    public List<HistoryItemModel> loadHistoryByOrderId(Long orderId) {
//        String hql = "from HistoryItemModel hi where hi.orderId =:orderId";
//        Query query = getSession().createQuery(hql);
//        query.setParameter("orderId", orderId);
//        return query.list();
//    }
}
