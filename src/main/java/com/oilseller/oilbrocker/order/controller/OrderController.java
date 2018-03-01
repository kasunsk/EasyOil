package com.oilseller.oilbrocker.order.controller;

import com.oilseller.oilbrocker.history.service.HistoryService;
import com.oilseller.oilbrocker.order.adaptor.param.*;
import com.oilseller.oilbrocker.order.dto.OrderPlacementRequest;
import com.oilseller.oilbrocker.order.dto.OrderPlacementResponse;
import com.oilseller.oilbrocker.order.param.*;
import com.oilseller.oilbrocker.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.PrimitiveIterator;


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

    @RequestMapping(value = "/place", method = RequestMethod.POST)
    public OrderPlacementResponseParam placeOrder(@RequestBody OrderPlacementRequestParam orderPlacementRequestParam) {
        OrderPlacementRequest orderPlacementRequest = orderPlacementParamAdaptor.fromParam(orderPlacementRequestParam);
        OrderPlacementResponse placementResponse = orderService.placeOrder(orderPlacementRequest);
        return responseParamAdaptor.fromDto(placementResponse);
    }

    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    public Long addCustomer(@RequestBody CustomerDetailsParam customerDetailsParam) {
        return orderService.addCustomerDetails(customerParamAdaptor.fromParam(customerDetailsParam));
    }

    @RequestMapping(value = "/customer/{customerId}", method = RequestMethod.GET)
    public CustomerDetailsParam getCustomerById(@PathVariable("customerId") Long customerId) {
        return (customerParamAdaptor.fromDto(orderService.getCustomerDetails(customerId)));
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<OrderDetailParam> viewOrders() {
        return orderDetailParamAdaptor.fromDtoList(orderService.viewOrders());
    }

    @RequestMapping(value = "/status/update", method = RequestMethod.POST)
    public Boolean updateOrderStatus(@RequestBody OrderStatusUpdateRequest statusUpdateRequest) {
        return orderService.updateOrderStatus(statusUpdateRequest.getOrderId(), statusUpdateRequest.getToOrderStatus());
    }

    @RequestMapping(value = "/history/{orderId}", method = RequestMethod.GET)
    public List<OrderHistoryParam> loadHistoryItemsByOrderId(@PathVariable("orderId") Long orderId) {
        return historyParamAdaptor.fromDtoList(historyService.loadHistoryItemByOrderId(orderId));
    }

}
