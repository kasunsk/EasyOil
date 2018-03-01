package com.oilseller.oilbrocker.order.dao;

import com.oilseller.oilbrocker.order.entity.OrderPlacementEntity;

import java.util.List;

public interface OrderDao {

    void saveOrUpdateOrder(OrderPlacementEntity placementEntity);

    List<OrderPlacementEntity> loadOrders();

    OrderPlacementEntity loadOrder(Long id);

}
