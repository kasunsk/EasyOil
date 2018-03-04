package com.oilseller.oilbrocker.order.dao.impl;

import com.oilseller.oilbrocker.order.dao.OrderDao;
import com.oilseller.oilbrocker.order.entity.OrderPlacementEntity;
import com.oilseller.oilbrocker.platform.dao.AbstractHibernateDao;
import com.oilseller.oilbrocker.platform.exception.dto.ServiceRuntimeException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("orderDao")
public class OrderDaoImpl extends AbstractHibernateDao implements OrderDao {

    @Override
    public void saveOrUpdateOrder(OrderPlacementEntity placementEntity) {
//        getSession().saveOrUpdate(placementEntity);
        throw new RuntimeException("Order_Placement_Error");
    }

    @Override
    public List<OrderPlacementEntity> loadOrders() {
        String hql = "from OrderPlacementEntity";
        Query query = getSession().createQuery(hql);
        return query.list();
    }

    @Override
    public OrderPlacementEntity loadOrder(Long orderId) {
        return getSession().get(OrderPlacementEntity.class, orderId);
    }
}
