package com.oilseller.oilbrocker.order.entity;

import com.oilseller.oilbrocker.order.dto.OrderStatus;
import com.oilseller.oilbrocker.order.dto.PaymentStatus;
import com.oilseller.oilbrocker.order.dto.PaymentType;
import com.oilseller.oilbrocker.platform.entity.AbstractEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PLACED_ORDER")
public class OrderPlacementEntity extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID", nullable = false)
    private Long orderId;

    @Column(name = "ORDER_REFERENCE", nullable = false)
    private String orderReference;

    @Column(name = "PAYMENT_REFERENCE", nullable = false)
    private String paymentReference;

    @Column(name = "CUSTOMER_ID", nullable = false)
    private Long customerId;

    @Column(name = "ORDER_ITEM", nullable = false)
    private String OrderItem;

    @Column(name = "AMOUNT", nullable = false)
    private Long amount;

    @Column(name = "PRICE", nullable = false)
    private Long price;

    @Column(name = "CURRENCY", nullable = false)
    private String currency;

    @Enumerated(EnumType.STRING)
    @Column(name = "PAYMENT_TYPE", nullable = false)
    private PaymentType paymentType;

    @Enumerated(EnumType.STRING)
    @Column(name = "PAYMENT_STATUS", nullable = false)
    private PaymentStatus paymentStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false)
    private OrderStatus orderStatus;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderReference() {
        return orderReference;
    }

    public void setOrderReference(String orderReference) {
        this.orderReference = orderReference;
    }

    public String getPaymentReference() {
        return paymentReference;
    }

    public void setPaymentReference(String paymentReference) {
        this.paymentReference = paymentReference;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getOrderItem() {
        return OrderItem;
    }

    public void setOrderItem(String orderItem) {
        OrderItem = orderItem;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
