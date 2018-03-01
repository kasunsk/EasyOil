package com.oilseller.oilbrocker.order.adaptor.param;

import com.oilseller.oilbrocker.order.dto.OrderPlacementResponse;
import com.oilseller.oilbrocker.order.param.OrderPlacementResponseParam;
import com.oilseller.oilbrocker.platform.adaptor.AbstractParamAdaptor;
import org.modelmapper.PropertyMap;

public class OrderPlacementResponseParamAdaptor extends AbstractParamAdaptor<OrderPlacementResponseParam, OrderPlacementResponse> {


    public OrderPlacementResponseParamAdaptor() {
        super(OrderPlacementResponseParam.class, OrderPlacementResponse.class);
    }

    @Override
    protected PropertyMap<OrderPlacementResponseParam, OrderPlacementResponse> fromParamMappings() {
        return new PropertyMap<OrderPlacementResponseParam, OrderPlacementResponse>() {
            @Override
            protected void configure() {
            }
        };
    }

    @Override
    protected PropertyMap<OrderPlacementResponse, OrderPlacementResponseParam> fromDtoMappings() {
        return new PropertyMap<OrderPlacementResponse, OrderPlacementResponseParam>() {
            @Override
            protected void configure() {
            }
        };
    }
}
