package com.oilseller.oilbrocker.order.adaptor.model;

import com.oilseller.oilbrocker.order.dto.OrderDetail;
import com.oilseller.oilbrocker.order.entity.OrderPlacementEntity;
import com.oilseller.oilbrocker.platform.adaptor.AbstractModelAdaptor;
import org.modelmapper.PropertyMap;

public class OrderDetailModelAdaptor extends AbstractModelAdaptor<OrderPlacementEntity, OrderDetail> {

    public OrderDetailModelAdaptor() {
        super(OrderPlacementEntity.class, OrderDetail.class);
    }

    @Override
    protected PropertyMap<OrderPlacementEntity, OrderDetail> fromModelMappings() {
        return new PropertyMap<OrderPlacementEntity, OrderDetail>() {
            @Override
            protected void configure() {
            }
        };
    }

    @Override
    protected PropertyMap<OrderDetail, OrderPlacementEntity> fromDtoMappings() {
        return new PropertyMap<OrderDetail, OrderPlacementEntity>() {
            @Override
            protected void configure() {
            }
        };
    }
}
