package com.oilseller.oilbrocker.sellingItem.dao;

import com.oilseller.oilbrocker.sellingItem.entity.SellingItemEntity;

import java.util.List;

/**
 * Created by kasun on 2/21/18.
 */
public interface SellingItemDao {

    List<SellingItemEntity> getAvailableSellingItems();

    Long addSellingItem(SellingItemEntity sellingItemEntity);
}
