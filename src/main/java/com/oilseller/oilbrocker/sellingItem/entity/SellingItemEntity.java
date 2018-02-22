package com.oilseller.oilbrocker.sellingItem.entity;

import com.sun.xml.internal.ws.spi.db.DatabindingException;

import java.util.Date;

/**
 * Created by kasun on 2/21/18.
 */
public class SellingItemEntity {

    private Long id;
    private String sellingItem;
    private String description;
    private Long price;
    private String currency;
    private String image;
    private Date createdDate;
    private Date lastModifiedDate;
    private Long version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSellingItem() {
        return sellingItem;
    }

    public void setSellingItem(String sellingItem) {
        this.sellingItem = sellingItem;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
