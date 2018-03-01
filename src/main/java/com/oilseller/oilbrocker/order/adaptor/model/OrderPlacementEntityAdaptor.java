package com.oilseller.oilbrocker.order.adaptor.model;

import com.oilseller.oilbrocker.order.dto.OrderPlacementRequest;
import com.oilseller.oilbrocker.order.entity.OrderPlacementEntity;
import com.oilseller.oilbrocker.platform.adaptor.AbstractModelAdaptor;
import org.modelmapper.PropertyMap;

public class OrderPlacementEntityAdaptor extends AbstractModelAdaptor<OrderPlacementEntity, OrderPlacementRequest> {

    public OrderPlacementEntityAdaptor() {
        super(OrderPlacementEntity.class, OrderPlacementRequest.class);
    }

    @Override
    protected PropertyMap<OrderPlacementEntity, OrderPlacementRequest> fromModelMappings() {
        return new PropertyMap<OrderPlacementEntity, OrderPlacementRequest>() {
            @Override
            protected void configure() {
            }
        };
    }

    @Override
    protected PropertyMap<OrderPlacementRequest, OrderPlacementEntity> fromDtoMappings() {
        return new PropertyMap<OrderPlacementRequest, OrderPlacementEntity>() {
            @Override
            protected void configure() {
            }
        };
    }
}
