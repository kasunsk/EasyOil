package com.oilseller.oilbrocker.history.adaptor;

import com.oilseller.oilbrocker.history.dto.HistoryItem;
import com.oilseller.oilbrocker.history.model.HistoryItemModel;
import com.oilseller.oilbrocker.platform.adaptor.AbstractModelAdaptor;
import org.modelmapper.PropertyMap;

public class HistoryItemModelAdaptor extends AbstractModelAdaptor<HistoryItemModel, HistoryItem>{

    public HistoryItemModelAdaptor() {
        super(HistoryItemModel.class, HistoryItem.class);
    }

    @Override
    protected PropertyMap<HistoryItemModel, HistoryItem> fromModelMappings() {
        return new PropertyMap<HistoryItemModel, HistoryItem>() {
            @Override
            protected void configure() {
            }
        };
    }

    @Override
    protected PropertyMap<HistoryItem, HistoryItemModel> fromDtoMappings() {
        return new PropertyMap<HistoryItem, HistoryItemModel>() {
            @Override
            protected void configure() {
            }
        };
    }
}
