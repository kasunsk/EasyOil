package com.oilseller.oilbrocker.order.param;

import com.oilseller.oilbrocker.order.dto.OrderStatus;

public class OrderStatusUpdateRequest {

    private Long orderId;
    private OrderStatus toOrderStatus;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public OrderStatus getToOrderStatus() {
        return toOrderStatus;
    }

    public void setToOrderStatus(OrderStatus toOrderStatus) {
        this.toOrderStatus = toOrderStatus;
    }
}
