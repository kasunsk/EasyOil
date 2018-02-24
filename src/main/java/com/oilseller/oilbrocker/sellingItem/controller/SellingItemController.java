package com.oilseller.oilbrocker.sellingItem.controller;

import com.oilseller.oilbrocker.sellingItem.adaptor.SellingItemParamAdaptor;
import com.oilseller.oilbrocker.sellingItem.param.SellingItemParam;
import com.oilseller.oilbrocker.sellingItem.service.SellingItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by kasun on 2/21/18.
 */
@RestController
@RequestMapping("/sellitem")
public class SellingItemController {

    private SellingItemService sellingItemService;

    private SellingItemParamAdaptor sellingItemParamAdaptor = new SellingItemParamAdaptor();

    @Autowired
    public SellingItemController(SellingItemService sellingItemService) {
        this.sellingItemService = sellingItemService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<SellingItemParam> getSellingItems() {
        return sellingItemParamAdaptor.fromDtoList(sellingItemService.loadAllSellingItems());
    }

    @RequestMapping(method = RequestMethod.POST)
    public Long addSellingItem(@RequestBody SellingItemParam sellingItemParam) {
        return sellingItemService.addSellingItem(sellingItemParamAdaptor.fromParam(sellingItemParam));
    }

}
