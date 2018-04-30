package com.oilseller.oilbrocker.product.service.impl;

import com.oilseller.oilbrocker.platform.exception.dto.ErrorCode;
import com.oilseller.oilbrocker.platform.exception.dto.ServiceRuntimeException;
import com.oilseller.oilbrocker.product.adaptor.ProductAdaptor;
import com.oilseller.oilbrocker.product.dao.ProductDao;
import com.oilseller.oilbrocker.product.dto.Product;
import com.oilseller.oilbrocker.product.dto.ProductStatus;
import com.oilseller.oilbrocker.product.entity.ProductEntity;
import com.oilseller.oilbrocker.product.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

import static com.oilseller.oilbrocker.platform.util.ValidationUtil.validate;

/**
 * Created by kasun on 2/21/18.
 */
@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);
    private static final String CREATE = "CREATE";
    private static final String UPDATE = "UPDATE";

    private ProductDao productDao;

    @Autowired
    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    private ProductAdaptor productAdaptor = new ProductAdaptor();

    @Transactional
    @Override
    public List<Product> loadAllProducts() {
        return productAdaptor.fromModelList((Collection<ProductEntity>) productDao.findAll());
    }

    @Transactional
    @Override
    public Long addProduct(Product product) {
        validateSellingItem(product, CREATE);
        ProductEntity currentItem = productDao.findByItemReference(product.getItemReference());
        if (currentItem != null) {
            throw new ServiceRuntimeException(ErrorCode.ALREADY_EXIST, "Item reference already exist");
        }
        return productDao.save(productAdaptor.fromDto(product)).getId();
    }

    @Transactional
    @Override
    public Product updateProduct(Product product) {

        validateSellingItem(product, UPDATE);
        ProductEntity productEntity = productDao.findOne(product.getId());

        ProductEntity existItem = productDao.findByItemReference(product.getItemReference());

        if (existItem != null && !existItem.getId().equals(productEntity.getId())) {
            throw new ServiceRuntimeException(ErrorCode.ALREADY_EXIST, "Item Reference Already Available");
        }
        productEntity.setAvailableAmount(product.getAvailableAmount());
        productEntity.setValidTo(product.getValidTo());
        productEntity.setCurrency(product.getCurrency());
        productEntity.setDescription(product.getSellingItem());
        productEntity.setImage(product.getImage());
        productEntity.setPrice(product.getPrice());
        productEntity.setStatus(product.getStatus());
        productEntity.setSellingItem(product.getSellingItem());
        productEntity.setItemReference(product.getItemReference());
        ProductEntity updatedItem = productDao.save(productEntity);
        return productAdaptor.fromModel(updatedItem);
    }

    @Transactional
    @Override
    public Boolean reduceProductAmount(Long itemId, Long reduceAmount) {
        ProductEntity sellingItem = productDao.findOne(itemId);
        Long availableAmount = sellingItem.getAvailableAmount();

        if (reduceAmount > availableAmount) {
            log.error("User requested items than available");
            throw new ServiceRuntimeException(ErrorCode.INVALID_INPUT, "Max " + availableAmount + " Items Only");
        }
        Long newAmount = availableAmount - reduceAmount;
        sellingItem.setAvailableAmount(newAmount);
        productDao.save(sellingItem);
        return Boolean.TRUE;
    }

    @Transactional
    @Override
    public Boolean removeProduct(Long productId) {
        ProductEntity product = productDao.findOne(productId);

        if (product == null) {
            log.error("Product not found for id : {}", productId);
            throw new ServiceRuntimeException(ErrorCode.NOT_FOUND, "Product Not Found");
        }
        productDao.delete(productId);
        return Boolean.TRUE;
    }

    @Transactional
    @Override
    public Product loadProduct(Long orderItemId) {
        return productAdaptor.fromModel(productDao.findOne(orderItemId));
    }

    private void validateSellingItem(Product product, String requestType) {

        validate(product, "selling item is null");
        if (requestType.equals(UPDATE)) {
            validate(product.getId(), "selling item id is required");
        }
        validate(product.getItemReference(), "selling item reference is required");
        validate(product.getSellingItem(), "Selling item name is empty");
        validate(product.getAvailableAmount(), "Available amount is empty");
        validate(product.getPrice(), "Item price is empty");
        validate(product.getCurrency(), "Currency is empty");
        validate(product.getImage(), "Image url is empty");
        validate(product.getValidTo(), "Valid to date is empty");

        if (product.getStatus() == null) {
            product.setStatus(ProductStatus.AVAILABLE);
        }
    }
}
