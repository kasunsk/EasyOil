package com.oilseller.oilbrocker.sellingItem.service;

import com.oilseller.oilbrocker.sellingItem.dto.SellingItem;

import java.util.List;

/**
 * Created by kasun on 2/21/18.
 */
public interface SellingItemService {

    List<SellingItem> loadAllSellingItems();

    Long addSellingItem(SellingItem sellingItem);

    SellingItem loadSellingItem(Long orderItem);
}
