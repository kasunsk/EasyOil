package com.oilseller.oilbrocker.sellingItem.service;

import com.oilseller.oilbrocker.sellingItem.adaptor.SellingItemAdaptor;
import com.oilseller.oilbrocker.sellingItem.dao.SellingItemDao;
import com.oilseller.oilbrocker.sellingItem.dto.SellingItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    @Override
    public List<SellingItem> loadAllSellingItems() {
        return sellingItemAdaptor.fromModelList(sellingItemDao.getAvailableSellingItems());
    }

    @Transactional
    @Override
    public Long addSellingItem(SellingItem sellingItem) {
        validateSellingItem(sellingItem);
        return sellingItemDao.addSellingItem(sellingItemAdaptor.fromDto(sellingItem));
    }

    @Transactional
    @Override
    public SellingItem loadSellingItem(Long orderItemId) {
        return sellingItemAdaptor.fromModel(sellingItemDao.loadSellingItem(orderItemId));
    }

    private void validateSellingItem(SellingItem sellingItem) {

    }
}
