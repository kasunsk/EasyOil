package com.oilseller.oilbrocker.order.service;

import com.oilseller.oilbrocker.order.dto.*;

import java.util.List;

public interface OrderService {

    Long addCustomerDetails(Customer customer);

    Customer getCustomerDetails(Long customerId);

    String placeOrder(OrderPlacementRequest orderPlacementRequest);

    Order loadOrder(Long orderId);

    Order loadOrderByReference(String reference);

    List<OrderDetail> viewOrders();

    Boolean updateOrderStatus(Long orderId, OrderStatus orderStatus);

    Order update(Order order);
}
