package com.oilseller.oilbrocker.order.param;

import com.oilseller.oilbrocker.order.dto.OrderStatus;

public class OrderPlacementResponseParam {

    private String orderReference;
    private OrderStatus orderStatus;

    public String getOrderReference() {
        return orderReference;
    }

    public void setOrderReference(String orderReference) {
        this.orderReference = orderReference;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
