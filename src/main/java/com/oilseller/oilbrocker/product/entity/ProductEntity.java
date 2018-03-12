package com.oilseller.oilbrocker.product.entity;

import com.oilseller.oilbrocker.platform.entity.AbstractTrackableEntity;
import com.oilseller.oilbrocker.product.dto.ProductStatus;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by kasun on 2/21/18.
 */
@Entity
@Table(name = "PRODUCT")
public class ProductEntity extends AbstractTrackableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM_ID", nullable = false)
    private Long id;

    @Column(name = "ITEM_REFERENCE", nullable = false)
    private String itemReference;

    @Column(name = "ITEM_NAME", nullable = false)
    private String sellingItem;

    @Column(name = "AVAILABLE_AMOUNT", nullable = false)
    private Long availableAmount;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false)
    private ProductStatus status;

    @Column(name = "PRICE", nullable = false)
    private Long price;

    @Column(name = "CURRENCY", nullable = false)
    private String currency;

    @Column(name = "IMAGE_URL", nullable = false)
    private String image;

    @Column(name = "VALID_TO", nullable = false)
    private Date validTo;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemReference() {
        return itemReference;
    }

    public void setItemReference(String itemReference) {
        this.itemReference = itemReference;
    }

    public String getSellingItem() {
        return sellingItem;
    }

    public void setSellingItem(String sellingItem) {
        this.sellingItem = sellingItem;
    }

    public Long getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(Long availableAmount) {
        this.availableAmount = availableAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getValidTo() {
        return validTo;
    }

    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }
}
