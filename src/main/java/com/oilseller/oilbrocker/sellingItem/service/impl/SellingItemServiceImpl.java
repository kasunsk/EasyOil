package com.oilseller.oilbrocker.sellingItem.service.impl;

import com.oilseller.oilbrocker.platform.exception.dto.ErrorCode;
import com.oilseller.oilbrocker.platform.exception.dto.ServiceRuntimeException;
import com.oilseller.oilbrocker.sellingItem.adaptor.SellingItemAdaptor;
import com.oilseller.oilbrocker.sellingItem.dao.SellingItemDao;
import com.oilseller.oilbrocker.sellingItem.dto.SellingItem;
import com.oilseller.oilbrocker.sellingItem.entity.SellingItemEntity;
import com.oilseller.oilbrocker.sellingItem.service.SellingItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by kasun on 2/21/18.
 */
@Service
public class SellingItemServiceImpl implements SellingItemService {

    private static final Logger log = LoggerFactory.getLogger(SellingItemServiceImpl.class);
    private static final String CREATE = "CREATE";
    private static final String UPDATE = "UPDATE";

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
        validateSellingItem(sellingItem, CREATE);
        SellingItemEntity currentItem = sellingItemDao.loadSellingItemByReference(sellingItem.getItemReference());
        if (currentItem != null) {
            throw new ServiceRuntimeException(ErrorCode.ALREADY_EXIST, "Item reference already exist");
        }
        return sellingItemDao.addSellingItem(sellingItemAdaptor.fromDto(sellingItem));
    }

    @Transactional
    @Override
    public SellingItem updateSellingItem(SellingItem sellingItem) {

        validateSellingItem(sellingItem, UPDATE);
        SellingItemEntity sellingItemEntity = sellingItemDao.loadSellingItem(sellingItem.getId());

        SellingItemEntity existItem = sellingItemDao.loadSellingItemByReference(sellingItem.getItemReference());

        if (existItem != null && !existItem.getId().equals(sellingItemEntity.getId())) {
            throw new ServiceRuntimeException(ErrorCode.ALREADY_EXIST, "Item Reference Already Available");
        }
        sellingItemEntity.setAvailableAmount(sellingItem.getAvailableAmount());
        sellingItemEntity.setValidTo(sellingItem.getValidTo());
        sellingItemEntity.setCurrency(sellingItem.getCurrency());
        sellingItemEntity.setDescription(sellingItem.getSellingItem());
        sellingItemEntity.setImage(sellingItem.getImage());
        sellingItemEntity.setPrice(sellingItem.getPrice());
        sellingItemEntity.setStatus(sellingItem.getStatus());
        sellingItemEntity.setSellingItem(sellingItem.getSellingItem());
        sellingItemEntity.setItemReference(sellingItem.getItemReference());
        SellingItemEntity updatedItem = sellingItemDao.updateSellingItem(sellingItemEntity);
        return sellingItemAdaptor.fromModel(updatedItem);
    }

    @Transactional
    @Override
    public Boolean reduceSellingItemAmount(Long itemId, Long reduceAmount) {
        SellingItemEntity sellingItem = sellingItemDao.loadSellingItem(itemId);
        Long availableAmount = sellingItem.getAvailableAmount();

        if (reduceAmount > availableAmount) {
            log.error("User requested items than available");
            throw new ServiceRuntimeException(ErrorCode.INVALID_INPUT, "Max " + availableAmount + " Items Only");
        }
        Long newAmount = availableAmount - reduceAmount;
        sellingItem.setAvailableAmount(newAmount);
        sellingItemDao.updateSellingItem(sellingItem);
        return Boolean.TRUE;
    }

    @Transactional
    @Override
    public SellingItem loadSellingItem(Long orderItemId) {
        return sellingItemAdaptor.fromModel(sellingItemDao.loadSellingItem(orderItemId));
    }

    private void validateSellingItem(SellingItem sellingItem, String requestType) {

        if (sellingItem == null) {
            throw new ServiceRuntimeException(ErrorCode.INVALID_INPUT, "selling item is null");
        }

        if (requestType.equals(UPDATE) && sellingItem.getId() == null) {
            throw new ServiceRuntimeException(ErrorCode.INVALID_INPUT, "selling item id is required");
        }

        if (sellingItem.getItemReference() == null || sellingItem.getItemReference().isEmpty()){
            throw new ServiceRuntimeException(ErrorCode.INVALID_INPUT, "selling item reference is required");
        }
    }
}
