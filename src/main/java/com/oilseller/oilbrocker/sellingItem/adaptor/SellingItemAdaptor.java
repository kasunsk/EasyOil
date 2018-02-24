package com.oilseller.oilbrocker.sellingItem.adaptor;

import com.oilseller.oilbrocker.platform.adaptor.AbstractModelAdaptor;
import com.oilseller.oilbrocker.platform.util.DateUtil;
import com.oilseller.oilbrocker.sellingItem.dto.SellingItem;
import com.oilseller.oilbrocker.sellingItem.entity.SellingItemEntity;
import org.modelmapper.PropertyMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kasun on 2/21/18.
 */
public class SellingItemAdaptor extends AbstractModelAdaptor<SellingItemEntity, SellingItem> {

    private static final Logger log = LoggerFactory.getLogger(SellingItemAdaptor.class);

    public SellingItemAdaptor() {
        super(SellingItemEntity.class, SellingItem.class);
    }


    public List<SellingItem> fromModelList(List<SellingItemEntity> sellingItems) {

        List<SellingItem> sellingItemList = new ArrayList<>();

        if (sellingItems != null) {
            for (SellingItemEntity sellingItemEntity : sellingItems) {
                sellingItemList.add(fromModel(sellingItemEntity));
            }
        }
        return sellingItemList;
    }

    @Override
    protected PropertyMap<SellingItemEntity, SellingItem> fromModelMappings() {
        return new PropertyMap<SellingItemEntity, SellingItem>() {
            @Override
            protected void configure() {
            }
        };
    }

    @Override
    protected PropertyMap<SellingItem, SellingItemEntity> fromDtoMappings() {
        return new PropertyMap<SellingItem, SellingItemEntity>() {
            @Override
            protected void configure() {
            }
        };
    }
}
