package com.oilseller.oilbrocker.order.service.impl;

import com.oilseller.oilbrocker.email.dto.EmailParam;
import com.oilseller.oilbrocker.email.service.EmailService;
import com.oilseller.oilbrocker.history.dto.HistoryItem;
import com.oilseller.oilbrocker.history.dto.HistoryType;
import com.oilseller.oilbrocker.history.service.HistoryService;
import com.oilseller.oilbrocker.order.adaptor.model.CustomerModelAdaptor;
import com.oilseller.oilbrocker.order.adaptor.model.OrderDetailModelAdaptor;
import com.oilseller.oilbrocker.order.adaptor.model.OrderPlacementEntityAdaptor;
import com.oilseller.oilbrocker.order.dao.CustomerDao;
import com.oilseller.oilbrocker.order.dao.OrderDao;
import com.oilseller.oilbrocker.order.dto.*;
import com.oilseller.oilbrocker.order.entity.OrderPlacementEntity;
import com.oilseller.oilbrocker.order.service.OrderService;
import com.oilseller.oilbrocker.platform.thread.ThreadLocalContext;
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
    private EmailService emailService;
    private HistoryService historyService;
    private SellingItemService sellingItemService;
    private CustomerModelAdaptor customerModelAdaptor = new CustomerModelAdaptor();
    private OrderPlacementEntityAdaptor orderPlacementModelAdaptor = new OrderPlacementEntityAdaptor();
    private OrderDetailModelAdaptor orderModelAdaptor = new OrderDetailModelAdaptor();

    @Autowired
    public OrderServiceImpl(CustomerDao customerDao, OrderDao orderDao ,SellingItemService sellingItemService,
                            EmailService emailService, HistoryService historyService) {
        this.customerDao = customerDao;
        this.sellingItemService = sellingItemService;
        this.orderDao = orderDao;
        this.emailService = emailService;
        this.historyService = historyService;
    }

    @Transactional
    @Override
    public Long addCustomerDetails(Customer customer) {
        validateCustomer(customer);
        return customerDao.addCustomer(customerModelAdaptor.fromDto(customer));
    }

    @Transactional
    @Override
    public Customer getCustomerDetails(Long customerId) {
        return customerModelAdaptor.fromModel(customerDao.loadCustomerById(customerId));
    }

    @Transactional
    @Override
    public List<OrderDetail> viewOrders() {
        return orderModelAdaptor.fromModelList(orderDao.loadOrders());
    }

    @Transactional
    @Override
    public Boolean updateOrderStatus(Long orderId, OrderStatus toOrderStatus) {
        OrderPlacementEntity order = orderDao.loadOrder(orderId);
        OrderStatus currentOrderStatus = order.getOrderStatus();
        order.setOrderStatus(toOrderStatus);
        orderDao.saveOrUpdateOrder(order);
        addHistoryItem(order,currentOrderStatus, toOrderStatus);
        sendOrderStatusUpdateEmail(toOrderStatus, order);
        return Boolean.TRUE;
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
        addHistoryItem(orderPlacementRequest, orderEntity);
        sendOrderCreateEmail(orderPlacementRequest, orderEntity, orderReference);
        OrderPlacementResponse orderPlacementResponse = new OrderPlacementResponse();
        orderPlacementResponse.setOrderReference(orderReference);
        orderPlacementResponse.setOrderStatus(orderEntity.getOrderStatus());
        return orderPlacementResponse;
    }

    private void sendOrderStatusUpdateEmail(OrderStatus toOrderStatus, OrderPlacementEntity order) {
        EmailParam emailParam = new EmailParam();
        emailParam.setSubject("Order Status Changed");
        emailParam.setReceiverAddress(customerDao.loadCustomerById(order.getCustomerId()).getEmail());
        emailParam.setContent(" Your order status changed to " + toOrderStatus);
        emailService.sendEmail(emailParam);
    }

    private void addHistoryItem(OrderPlacementRequest orderPlacementRequest, OrderPlacementEntity orderEntity) {
        HistoryItem historyItem = new HistoryItem();
        historyItem.setHistoryType(HistoryType.ORDER_CREATED);
        historyItem.setOrderId(orderEntity.getOrderId());
        historyItem.setSellingItemId(orderPlacementRequest.getOrderItemId());
        historyItem.setUsername("CUSTOMER");
        historyItem.setUserNote("Order Created");
        historyService.addHistoryItem(historyItem);
    }

    private void sendOrderCreateEmail(OrderPlacementRequest orderPlacementRequest, OrderPlacementEntity orderEntity, String orderReference) {
        EmailParam emailParam = new EmailParam();
        emailParam.setSubject("Order Placed");
        emailParam.setReceiverAddress(orderPlacementRequest.getCustomer().getEmail());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Order Reference : " + orderReference);
        stringBuilder.append(" Order Status : " + orderEntity.getOrderStatus());
        emailParam.setContent(stringBuilder.toString());
        emailService.sendEmail(emailParam);
    }

    private void addHistoryItem(OrderPlacementEntity order,OrderStatus currentStatus, OrderStatus toOrderStatus) {
        HistoryItem historyItem = new HistoryItem();
        historyItem.setHistoryType(HistoryType.ORDER_SC);
        historyItem.setOrderId(order.getOrderId());
        historyItem.setUsername(ThreadLocalContext.getUser());
        historyItem.setUserNote(order.getOrderItem());
        historyItem.setFromStatus(currentStatus);
        historyItem.setToStatus(toOrderStatus);
        historyService.addHistoryItem(historyItem);
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
