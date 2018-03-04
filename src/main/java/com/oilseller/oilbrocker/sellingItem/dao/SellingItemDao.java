package com.oilseller.oilbrocker.sellingItem.dao;

import com.oilseller.oilbrocker.sellingItem.entity.SellingItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Created by kasun on 2/21/18.
 */
@Repository("sellingItemDao")
public interface SellingItemDao extends JpaRepository<SellingItemEntity, Long> {

    SellingItemEntity findByItemReference(String itemReference);
}
