package com.oilseller.oilbrocker.order.adaptor.param;

import com.oilseller.oilbrocker.order.dto.OrderPlacementRequest;
import com.oilseller.oilbrocker.order.param.OrderPlacementRequestParam;
import com.oilseller.oilbrocker.platform.adaptor.AbstractParamAdaptor;
import org.modelmapper.PropertyMap;

public class OrderPlacementRequestParamAdaptor extends AbstractParamAdaptor<OrderPlacementRequestParam, OrderPlacementRequest> {

    private CustomerParamAdaptor customerParamAdaptor = new CustomerParamAdaptor();

    public OrderPlacementRequestParamAdaptor() {
        super(OrderPlacementRequestParam.class, OrderPlacementRequest.class);
    }

    @Override
    protected PropertyMap<OrderPlacementRequestParam, OrderPlacementRequest> fromParamMappings() {
        return new PropertyMap<OrderPlacementRequestParam, OrderPlacementRequest>() {
            @Override
            protected void configure() {
            }
        };
    }

    @Override
    protected PropertyMap<OrderPlacementRequest, OrderPlacementRequestParam> fromDtoMappings() {
        return new PropertyMap<OrderPlacementRequest, OrderPlacementRequestParam>() {
            @Override
            protected void configure() {
            }
        };
    }

    @Override
    public OrderPlacementRequest fromParam(OrderPlacementRequestParam document) {
        OrderPlacementRequest placementRequest = super.fromParam(document);
        placementRequest.setCustomer(customerParamAdaptor.fromParam(document.getCustomer()));
        return placementRequest;
    }
}
