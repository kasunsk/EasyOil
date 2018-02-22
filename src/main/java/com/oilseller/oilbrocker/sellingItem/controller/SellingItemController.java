package com.oilseller.oilbrocker.sellingItem.controller;

import com.oilseller.oilbrocker.sellingItem.dto.SellingItem;
import com.oilseller.oilbrocker.sellingItem.service.SellingItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by kasun on 2/21/18.
 */
@RestController("/sellitem")
public class SellingItemController {

    private SellingItemService sellingItemService;

    @Autowired
    public SellingItemController(SellingItemService sellingItemService) {
        this.sellingItemService = sellingItemService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<SellingItem> getSellingItems() {
        return sellingItemService.loadAllSellingItems();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Long addSellingItem(@RequestParam SellingItem sellingItem) {
        return sellingItemService.addSellingItem(sellingItem);
    }

}
