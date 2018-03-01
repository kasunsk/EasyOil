package com.oilseller.oilbrocker.order.adaptor.param;

import com.oilseller.oilbrocker.history.dto.HistoryItem;
import com.oilseller.oilbrocker.order.param.OrderHistoryParam;
import com.oilseller.oilbrocker.platform.adaptor.AbstractParamAdaptor;
import com.oilseller.oilbrocker.platform.util.DateUtil;
import org.modelmapper.PropertyMap;

public class OrderHistoryParamAdaptor extends AbstractParamAdaptor<OrderHistoryParam, HistoryItem> {
    public OrderHistoryParamAdaptor() {
        super(OrderHistoryParam.class, HistoryItem.class);
    }

    @Override
    protected PropertyMap<OrderHistoryParam, HistoryItem> fromParamMappings() {
        return new PropertyMap<OrderHistoryParam, HistoryItem>() {
            @Override
            protected void configure() {
            }
        };
    }

    @Override
    protected PropertyMap<HistoryItem, OrderHistoryParam> fromDtoMappings() {
        return new PropertyMap<HistoryItem, OrderHistoryParam>() {
            @Override
            protected void configure() {
            }
        };
    }

    @Override
    public OrderHistoryParam fromDto(HistoryItem historyItem) {
        OrderHistoryParam orderHistoryParam = super.fromDto(historyItem);
        orderHistoryParam.setHistoryDate(DateUtil.toSimpleDate(historyItem.getCreatedDate()));
        return orderHistoryParam;
    }
}
