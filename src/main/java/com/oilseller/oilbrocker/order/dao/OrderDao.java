package com.oilseller.oilbrocker.order.dao;

import com.oilseller.oilbrocker.order.entity.OrderPlacementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("orderDao")
public interface OrderDao extends JpaRepository<OrderPlacementEntity, Long> {

//    void saveOrUpdateOrder(OrderPlacementEntity placementEntity);
//
//    List<OrderPlacementEntity> loadOrders();
//
//    OrderPlacementEntity loadOrder(Long id);

}
