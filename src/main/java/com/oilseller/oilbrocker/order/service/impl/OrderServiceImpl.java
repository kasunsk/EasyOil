package com.oilseller.oilbrocker.order.service.impl;

import com.oilseller.oilbrocker.order.adaptor.CustomerModelAdaptor;
import com.oilseller.oilbrocker.order.adaptor.OrderPlacementEntityAdaptor;
import com.oilseller.oilbrocker.order.dao.CustomerDao;
import com.oilseller.oilbrocker.order.dao.OrderDao;
import com.oilseller.oilbrocker.order.dto.Customer;
import com.oilseller.oilbrocker.order.dto.OrderPlacementRequest;
import com.oilseller.oilbrocker.order.dto.OrderPlacementResponse;
import com.oilseller.oilbrocker.order.dto.OrderStatus;
import com.oilseller.oilbrocker.order.entity.OrderPlacementEntity;
import com.oilseller.oilbrocker.order.service.OrderService;
import com.oilseller.oilbrocker.sellingItem.dto.SellingItem;
import com.oilseller.oilbrocker.sellingItem.service.SellingItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

    private CustomerDao customerDao;
    private OrderDao orderDao;
    private SellingItemService sellingItemService;
    private CustomerModelAdaptor customerModelAdaptor = new CustomerModelAdaptor();
    private OrderPlacementEntityAdaptor orderPlacementModelAdaptor = new OrderPlacementEntityAdaptor();

    @Autowired
    public OrderServiceImpl(CustomerDao customerDao, OrderDao orderDao ,SellingItemService sellingItemService) {
        this.customerDao = customerDao;
        this.sellingItemService = sellingItemService;
        this.orderDao = orderDao;
    }

    @Transactional
    @Override
    public Long addCustomerDetails(Customer customer) {
        validateCustomer(customer);
        return customerDao.addCustomer(customerModelAdaptor.fromDto(customer));
    }

    @Transactional
    @Override
    public OrderPlacementResponse placeOrder(OrderPlacementRequest orderPlacementRequest) {
        validateOrderPlacement(orderPlacementRequest);
        OrderPlacementEntity orderEntity = orderPlacementModelAdaptor.fromDto(orderPlacementRequest);
        String orderReference = generateOrderReference(orderPlacementRequest);
        orderEntity.setOrderReference(orderReference);
        SellingItem sellingItem = sellingItemService.loadSellingItem(orderPlacementRequest.getOrderItemId());
        orderEntity.setOrderItem(sellingItem.getSellingItem());
        Long customerId = customerDao.addCustomer(customerModelAdaptor.fromDto(orderPlacementRequest.getCustomer()));
        orderEntity.setCustomerId(customerId);
        orderEntity.setPaymentReference("ON_DELIVERY");
        orderEntity.setOrderStatus(OrderStatus.PLACED);
        orderDao.createOrder(orderEntity);

        OrderPlacementResponse orderPlacementResponse = new OrderPlacementResponse();
        orderPlacementResponse.setOrderReference(orderReference);
        orderPlacementResponse.setOrderStatus(orderEntity.getOrderStatus());
        return orderPlacementResponse;
    }

    private String generateOrderReference(OrderPlacementRequest orderPlacementRequest) {
        return (new Date()).getTime() + orderPlacementRequest.getOrderItemId() + orderPlacementRequest.getCustomer().getMobileNumber();
    }

    private void validateOrderPlacement(OrderPlacementRequest orderPlacementRequest) {

    }

    private void validateCustomer(Customer customer) {

    }
}
