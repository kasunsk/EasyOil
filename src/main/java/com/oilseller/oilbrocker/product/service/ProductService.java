package com.oilseller.oilbrocker.product.service;

import com.oilseller.oilbrocker.product.dto.Product;

import java.util.List;

/**
 * Created by kasun on 2/21/18.
 */
public interface ProductService {

    List<Product> loadAllProducts();

    Long addProduct(Product product);

    Product updateProduct(Product product);

    Boolean reduceProductAmount(Long itemId, Long reduceAmount);

    Boolean removeProduct(Long productId);

    Product loadProduct(Long orderItem);
}
