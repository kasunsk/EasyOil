package com.oilseller.oilbrocker.sellingItem.service;

import com.oilseller.oilbrocker.sellingItem.adaptor.SellingItemAdaptor;
import com.oilseller.oilbrocker.sellingItem.dao.SellingItemDao;
import com.oilseller.oilbrocker.sellingItem.dto.SellingItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kasun on 2/21/18.
 */
@Service
public class SellingItemServiceImpl implements SellingItemService {

    private SellingItemDao sellingItemDao;

    @Autowired
    public SellingItemServiceImpl(SellingItemDao sellingItemDao) {
        this.sellingItemDao = sellingItemDao;
    }

    private SellingItemAdaptor sellingItemAdaptor = new SellingItemAdaptor();

    @Override
    public List<SellingItem> loadAllSellingItems() {
        return sellingItemAdaptor.adapt(sellingItemDao.getSellingItems());
    }

    @Override
    public Long addSellingItem(SellingItem sellingItem) {
        validateSellingItem(sellingItem);
        return sellingItemDao.addSellingItem(sellingItemAdaptor.adapt(sellingItem));
    }

    private void validateSellingItem(SellingItem sellingItem) {

    }
}
