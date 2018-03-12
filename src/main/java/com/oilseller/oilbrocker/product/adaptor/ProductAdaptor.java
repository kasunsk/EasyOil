package com.oilseller.oilbrocker.product.adaptor;

import com.oilseller.oilbrocker.platform.adaptor.AbstractModelAdaptor;
import com.oilseller.oilbrocker.product.dto.Product;
import com.oilseller.oilbrocker.product.entity.ProductEntity;
import org.modelmapper.PropertyMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kasun on 2/21/18.
 */
public class ProductAdaptor extends AbstractModelAdaptor<ProductEntity, Product> {

    private static final Logger log = LoggerFactory.getLogger(ProductAdaptor.class);

    public ProductAdaptor() {
        super(ProductEntity.class, Product.class);
    }


    public List<Product> fromModelList(List<ProductEntity> sellingItems) {

        List<Product> productList = new ArrayList<>();

        if (sellingItems != null) {
            for (ProductEntity productEntity : sellingItems) {
                productList.add(fromModel(productEntity));
            }
        }
        return productList;
    }

    @Override
    protected PropertyMap<ProductEntity, Product> fromModelMappings() {
        return new PropertyMap<ProductEntity, Product>() {
            @Override
            protected void configure() {
            }
        };
    }

    @Override
    protected PropertyMap<Product, ProductEntity> fromDtoMappings() {
        return new PropertyMap<Product, ProductEntity>() {
            @Override
            protected void configure() {
            }
        };
    }
}
