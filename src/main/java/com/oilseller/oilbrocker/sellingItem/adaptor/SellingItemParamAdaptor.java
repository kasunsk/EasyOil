package com.oilseller.oilbrocker.sellingItem.adaptor;

import com.oilseller.oilbrocker.platform.adaptor.AbstractParamAdaptor;
import com.oilseller.oilbrocker.platform.util.DateUtil;
import com.oilseller.oilbrocker.sellingItem.dto.SellingItem;
import com.oilseller.oilbrocker.sellingItem.param.SellingItemParam;
import org.modelmapper.PropertyMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;

public class SellingItemParamAdaptor extends AbstractParamAdaptor<SellingItemParam, SellingItem> {

    private static final Logger log = LoggerFactory.getLogger(SellingItemParamAdaptor.class);

    public SellingItemParamAdaptor() {
        super(SellingItemParam.class, SellingItem.class);
    }

    @Override
    public SellingItemParam fromDto(SellingItem sellingItem) {
        SellingItemParam sellingItemParam = super.fromDto(sellingItem);
        sellingItemParam.setValidUntil(DateUtil.toSimpleDate(sellingItem.getValidTo()));
        sellingItemParam.setCreatedDate(DateUtil.toSimpleDate(sellingItem.getCreatedDate()));
        sellingItemParam.setLastModifiedDate(DateUtil.toSimpleDate(sellingItem.getLastModifiedDate()));
        return sellingItemParam;
    }

    @Override
    public SellingItem fromParam(SellingItemParam document) {
        SellingItem sellingItem = super.fromParam(document);
        try {
            sellingItem.setValidTo(DateUtil.fromSimpleDateStringToDate(document.getValidUntil()));
        } catch (ParseException e) {
            log.error("Invalid Date Option", e);
            throw new RuntimeException(e);
        }
        return sellingItem;
    }

    @Override
    protected PropertyMap<SellingItemParam, SellingItem> fromParamMappings() {
        return new PropertyMap<SellingItemParam, SellingItem>() {
            @Override
            protected void configure() {
            }
        };
    }

    @Override
    protected PropertyMap<SellingItem, SellingItemParam> fromDtoMappings() {
        return new PropertyMap<SellingItem, SellingItemParam>() {
            @Override
            protected void configure() {
            }
        };
    }
}
