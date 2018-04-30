package com.oilseller.oilbrocker.product.adaptor;

import com.oilseller.oilbrocker.platform.adaptor.AbstractParamAdaptor;
import com.oilseller.oilbrocker.platform.util.DateUtil;
import com.oilseller.oilbrocker.product.dto.Product;
import com.oilseller.oilbrocker.product.param.ProductParam;
import org.modelmapper.PropertyMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;

public class ProductParamAdaptor extends AbstractParamAdaptor<ProductParam, Product> {

    private static final Logger log = LoggerFactory.getLogger(ProductParamAdaptor.class);

    public ProductParamAdaptor() {
        super(ProductParam.class, Product.class);
    }

    @Override
    public ProductParam fromDto(Product product) {
        ProductParam productParam = super.fromDto(product);
        productParam.setValidUntil(DateUtil.toSimpleDateWithoutTime(product.getValidTo()));
        productParam.setCreatedDate(DateUtil.toSimpleDate(product.getCreatedDate()));
        productParam.setLastModifiedDate(DateUtil.toSimpleDate(product.getLastModifiedDate()));
        return productParam;
    }

    @Override
    public Product fromParam(ProductParam document) {
        document.setLastModifiedDate(null);
        document.setCreatedDate(null);

        Product product = super.fromParam(document);
        try {
            product.setValidTo(DateUtil.fromSimpleDateStringToSimpleDate(document.getValidUntil()));
        } catch (ParseException e) {
            log.error("Invalid Date Option", e);
            throw new RuntimeException(e);
        }
        return product;
    }

    @Override
    protected PropertyMap<ProductParam, Product> fromParamMappings() {
        return new PropertyMap<ProductParam, Product>() {
            @Override
            protected void configure() {
            }
        };
    }

    @Override
    protected PropertyMap<Product, ProductParam> fromDtoMappings() {
        return new PropertyMap<Product, ProductParam>() {
            @Override
            protected void configure() {
            }
        };
    }
}
