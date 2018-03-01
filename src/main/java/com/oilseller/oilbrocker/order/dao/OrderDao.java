package com.oilseller.oilbrocker.order.dao;

import com.oilseller.oilbrocker.order.entity.OrderPlacementEntity;

public interface OrderDao {

    void createOrder(OrderPlacementEntity placementEntity);

}
