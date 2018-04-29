package com.oilseller.oilbrocker.order.controller;

import com.oilseller.oilbrocker.history.service.HistoryService;
import com.oilseller.oilbrocker.order.adaptor.param.*;
import com.oilseller.oilbrocker.order.dto.*;
import com.oilseller.oilbrocker.order.param.*;
import com.oilseller.oilbrocker.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService;
    private HistoryService historyService;
    private CustomerParamAdaptor customerParamAdaptor = new CustomerParamAdaptor();
    private OrderPlacementRequestParamAdaptor orderPlacementParamAdaptor = new OrderPlacementRequestParamAdaptor();
    private OrderPlacementResponseParamAdaptor responseParamAdaptor = new OrderPlacementResponseParamAdaptor();
    private OrderDetailParamAdaptor orderDetailParamAdaptor = new OrderDetailParamAdaptor();
    private OrderHistoryParamAdaptor historyParamAdaptor = new OrderHistoryParamAdaptor();


    @Autowired
    public OrderController(OrderService orderService, HistoryService historyService) {
        this.orderService = orderService;
        this.historyService =historyService;
    }

    @CrossOrigin
    @RequestMapping(value = "/place", method = RequestMethod.POST)
    public OrderPlacementResponse placeOrder(@RequestBody OrderPlacementRequestParam orderPlacementRequestParam) {
        OrderPlacementRequest orderPlacementRequest = orderPlacementParamAdaptor.fromParam(orderPlacementRequestParam);
        OrderPlacementResponse response = new OrderPlacementResponse();
        response.setOrderReference(orderService.placeOrder(orderPlacementRequest));
        response.setOrderStatus(OrderStatus.PLACED);
        return response;
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.PUT)
    public Order update(@RequestBody Order order) {
        return orderService.update(order);
    }

    @CrossOrigin
    @RequestMapping(value = "/load/{reference}", method = RequestMethod.GET)
    public Order loadOrderByReference(@PathVariable("reference") String reference) {
        return orderService.loadOrderByReference(reference);
    }

    @CrossOrigin
    @RequestMapping(value = "/get/{orderId}", method = RequestMethod.GET)
    public Order loadOrderById(@PathVariable("orderId") Long orderId) {
        return orderService.loadOrder(orderId);
    }

    @CrossOrigin
    @RequestMapping(value = "/get/status", method = RequestMethod.GET)
    public List<OrderStatus> loadAvailableOrderStatus() {
        return Arrays.asList(OrderStatus.values());
    }

    @CrossOrigin
    @RequestMapping(value = "/get/payment/status", method = RequestMethod.GET)
    public List<PaymentStatus> loadAvailablePaymentStatus() {
        return Arrays.asList(PaymentStatus.values());
    }

    @CrossOrigin
    @RequestMapping(value = "/get/payment/type", method = RequestMethod.GET)
    public List<PaymentType> loadAvailablePaymentType() {
        return Arrays.asList(PaymentType.values());
    }


    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    public Long addCustomer(@RequestBody CustomerDetailsParam customerDetailsParam) {
        return orderService.addCustomerDetails(customerParamAdaptor.fromParam(customerDetailsParam));
    }

    @CrossOrigin
    @RequestMapping(value = "/customer/{customerId}", method = RequestMethod.GET)
    public CustomerDetailsParam getCustomerById(@PathVariable("customerId") Long customerId) {
        return (customerParamAdaptor.fromDto(orderService.getCustomerDetails(customerId)));
    }

    @CrossOrigin
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<OrderDetailParam> viewOrders() {
        return orderDetailParamAdaptor.fromDtoList(orderService.viewOrders());
    }

    @RequestMapping(value = "/status/update", method = RequestMethod.POST)
    public Boolean updateOrderStatus(@RequestBody OrderStatusUpdateRequest statusUpdateRequest) {
        return orderService.updateOrderStatus(statusUpdateRequest.getOrderId(), statusUpdateRequest.getToOrderStatus());
    }

    @CrossOrigin
    @RequestMapping(value = "/history/{orderId}", method = RequestMethod.GET)
    public List<OrderHistoryParam> loadHistoryItemsByOrderId(@PathVariable("orderId") Long orderId) {
        return historyParamAdaptor.fromDtoList(historyService.loadHistoryItemByOrderId(orderId));
    }

}
