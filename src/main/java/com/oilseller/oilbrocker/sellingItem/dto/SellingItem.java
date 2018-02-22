package com.oilseller.oilbrocker.sellingItem.dto;

/**
 * Created by kasun on 2/21/18.
 */
public class SellingItem {

    private Long id;
    private String sellingItem;
    private String description;
    private Long price;
    private String currency;
    private String image;

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
}
