package com.oilseller.oilbrocker.order.adaptor;

import com.oilseller.oilbrocker.order.dto.OrderDetail;
import com.oilseller.oilbrocker.order.param.OrderDetailParam;
import com.oilseller.oilbrocker.platform.adaptor.AbstractParamAdaptor;
import com.oilseller.oilbrocker.platform.util.DateUtil;
import org.modelmapper.PropertyMap;

public class OrderDetailParamAdaptor extends AbstractParamAdaptor<OrderDetailParam, OrderDetail> {

    public OrderDetailParamAdaptor() {
        super(OrderDetailParam.class, OrderDetail.class);
    }

    @Override
    protected PropertyMap<OrderDetailParam, OrderDetail> fromParamMappings() {
        return new PropertyMap<OrderDetailParam, OrderDetail>() {
            @Override
            protected void configure() {
            }
        };
    }

    @Override
    protected PropertyMap<OrderDetail, OrderDetailParam> fromDtoMappings() {
        return new PropertyMap<OrderDetail, OrderDetailParam>() {
            @Override
            protected void configure() {
            }
        };
    }

    @Override
    public OrderDetailParam fromDto(OrderDetail orderDetail) {
        OrderDetailParam detailParam = super.fromDto(orderDetail);
        detailParam.setLastModifiedDate(DateUtil.toSimpleDate(orderDetail.getLastModifiedDate()));
        detailParam.setCreatedDate(DateUtil.toSimpleDate(orderDetail.getCreatedDate()));
        return detailParam;
    }
}
