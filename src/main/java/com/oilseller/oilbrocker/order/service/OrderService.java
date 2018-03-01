package com.oilseller.oilbrocker.order.service;

import com.oilseller.oilbrocker.order.dto.Customer;
import com.oilseller.oilbrocker.order.dto.OrderPlacementRequest;
import com.oilseller.oilbrocker.order.dto.OrderPlacementResponse;

public interface OrderService {

    Long addCustomerDetails(Customer customer);

    OrderPlacementResponse placeOrder(OrderPlacementRequest orderPlacementRequest);
}
