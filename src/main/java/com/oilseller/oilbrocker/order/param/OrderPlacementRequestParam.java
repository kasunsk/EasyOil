package com.oilseller.oilbrocker.order.param;

public class OrderPlacementRequestParam {

    private CustomerDetailsParam customerDetailsParam;
    private Long orderItemId;
    private Long amount;
    private Long price;
    private String currency;
    private String paymentType;
    private String paymentStatus;

    public CustomerDetailsParam getCustomerDetailsParam() {
        return customerDetailsParam;
    }

    public void setCustomerDetailsParam(CustomerDetailsParam customerDetailsParam) {
        this.customerDetailsParam = customerDetailsParam;
    }

    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
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

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
