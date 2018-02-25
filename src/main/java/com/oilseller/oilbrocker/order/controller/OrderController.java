package com.oilseller.oilbrocker.order.controller;

import com.oilseller.oilbrocker.order.adaptor.CustomerParamAdaptor;
import com.oilseller.oilbrocker.order.param.CustomerDetailsParam;
import com.oilseller.oilbrocker.order.param.OrderDetailsParam;
import com.oilseller.oilbrocker.order.param.OrderPlacementRequestParam;
import com.oilseller.oilbrocker.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService;
    private CustomerParamAdaptor customerParamAdaptor =new CustomerParamAdaptor();

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(value = "/place", method = RequestMethod.POST)
    public OrderDetailsParam placeOrder(@RequestBody OrderPlacementRequestParam orderPlacementRequestParam) {
        return null;
    }

    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    public Long addCustomer(@RequestBody CustomerDetailsParam customerDetailsParam) {
        return orderService.addCustomerDetails(customerParamAdaptor.fromParam(customerDetailsParam));
    }

}
