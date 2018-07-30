package com.oilseller.oilbrocker.platform.interceptor;

public interface ApplicationHttpUri {

    String LOGIN = "login";
    String ERROR = "error";
    String ORDER_PLACE = "order/place";
    String PRODUCT_LIST = "product/list";
    String PRODUCT_LOAD = "product/load";
    String PRODUCT_AVAILABLE_STATUS = "product/available/status";
    String PRODUCT_AVAILABLE_CURRENCIES = "product/available/currencies";
    String ORDER_LOAD = "order/load";
    String ORDER_STATUS = "order/get/status";
    String ORDER_PAYMENT_STATUS = "order/get/payment/status";
    String ORDER_PAYMENT_TYPE = "order/get/payment/type";
}
