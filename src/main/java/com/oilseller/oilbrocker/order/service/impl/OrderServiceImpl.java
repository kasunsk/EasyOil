package com.oilseller.oilbrocker.order.service.impl;

import com.oilseller.oilbrocker.order.adaptor.model.CustomerModelAdaptor;
import com.oilseller.oilbrocker.order.adaptor.model.OrderDetailModelAdaptor;
import com.oilseller.oilbrocker.order.adaptor.model.OrderPlacementEntityAdaptor;
import com.oilseller.oilbrocker.order.dao.CustomerDao;
import com.oilseller.oilbrocker.order.dao.OrderDao;
import com.oilseller.oilbrocker.order.dto.*;
import com.oilseller.oilbrocker.order.entity.OrderPlacementEntity;
import com.oilseller.oilbrocker.order.service.OrderService;
import com.oilseller.oilbrocker.sellingItem.dto.SellingItem;
import com.oilseller.oilbrocker.sellingItem.service.SellingItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

    private CustomerDao customerDao;
    private OrderDao orderDao;
    private SellingItemService sellingItemService;
    private CustomerModelAdaptor customerModelAdaptor = new CustomerModelAdaptor();
    private OrderPlacementEntityAdaptor orderPlacementModelAdaptor = new OrderPlacementEntityAdaptor();
    private OrderDetailModelAdaptor orderModelAdaptor = new OrderDetailModelAdaptor();

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
        orderEntity.setPaymentReference("COD");
        orderEntity.setOrderStatus(OrderStatus.PLACED);
        orderDao.saveOrUpdateOrder(orderEntity);

        OrderPlacementResponse orderPlacementResponse = new OrderPlacementResponse();
        orderPlacementResponse.setOrderReference(orderReference);
        orderPlacementResponse.setOrderStatus(orderEntity.getOrderStatus());
        return orderPlacementResponse;
    }

    @Transactional
    @Override
    public List<OrderDetail> viewOrders() {
        return orderModelAdaptor.fromModelList(orderDao.loadOrders());
    }

    @Transactional
    @Override
    public Boolean updateOrderStatus(Long orderId, OrderStatus orderStatus) {
        OrderPlacementEntity order = orderDao.loadOrder(orderId);
        order.setOrderStatus(orderStatus);
        orderDao.saveOrUpdateOrder(order);
        return Boolean.TRUE;
    }

    private String generateOrderReference(OrderPlacementRequest orderPlacementRequest) {

        String name = orderPlacementRequest.getCustomer().getCustomerName();
        String partOne = name.substring(0, 2);

        String currentTime = new Date().getTime() + "";
        String partThree = currentTime.substring(currentTime.length() - 2, currentTime.length());

        String address = orderPlacementRequest.getCustomer().getCustomerAddress();
        String partTwo = address.substring(0,2);

        String partFour = orderPlacementRequest.getOrderItemId() + "";

        String mobile = orderPlacementRequest.getCustomer().getMobileNumber();
        String partFive = mobile.substring(mobile.length()-3, mobile.length());

        String reference = partOne + partTwo + partThree + partFour + partFive;
        return reference.toLowerCase().trim();
    }

    private void validateOrderPlacement(OrderPlacementRequest orderPlacementRequest) {

    }

    private void validateCustomer(Customer customer) {

    }
}
