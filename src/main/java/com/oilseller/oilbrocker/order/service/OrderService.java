package com.oilseller.oilbrocker.order.service;

import com.oilseller.oilbrocker.order.dto.*;

import java.util.List;

public interface OrderService {

    Long addCustomerDetails(Customer customer);

    Customer getCustomerDetails(Long customerId);

    OrderPlacementResponse placeOrder(OrderPlacementRequest orderPlacementRequest);

    List<OrderDetail> viewOrders();

    Boolean updateOrderStatus(Long orderId, OrderStatus orderStatus);
}
