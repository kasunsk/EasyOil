package com.oilseller.oilbrocker.product.dao;

import com.oilseller.oilbrocker.product.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Created by kasun on 2/21/18.
 */
@Repository("productDao")
public interface ProductDao extends JpaRepository<ProductEntity, Long> {

    ProductEntity findByItemReference(String itemReference);
}
