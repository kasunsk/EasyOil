package com.oilseller.oilbrocker.product.controller;

import com.oilseller.oilbrocker.platform.dto.Currency;
import com.oilseller.oilbrocker.product.adaptor.ProductParamAdaptor;
import com.oilseller.oilbrocker.product.dto.ProductStatus;
import com.oilseller.oilbrocker.product.param.ProductParam;
import com.oilseller.oilbrocker.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * Created by kasun on 2/21/18.
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    private ProductParamAdaptor productParamAdaptor = new ProductParamAdaptor();

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @CrossOrigin
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<ProductParam> getProduct() {
        return productParamAdaptor.fromDtoList(productService.loadAllProducts());
    }

    @CrossOrigin
    @RequestMapping(value = "/load/{productId}", method = RequestMethod.GET)
    public ProductParam getProduct(@PathVariable("productId") Long productId) {
        return productParamAdaptor.fromDto(productService.loadProduct(productId));
    }

    @CrossOrigin
    @RequestMapping(value = "/available/currencies",method = RequestMethod.GET)
    public List<Currency> loadAvailableCurrencies() {
        return Arrays.asList(Currency.values());
    }

    @CrossOrigin
    @RequestMapping(value = "/available/status",method = RequestMethod.GET)
    public List<ProductStatus> loadAvailableStatus() {
        return Arrays.asList(ProductStatus.values());
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST)
    public Long addProductItem(@RequestBody ProductParam productParam) {
        return productService.addProduct(productParamAdaptor.fromParam(productParam));
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.PUT)
    public Long updateProduct(@RequestBody ProductParam productParam) {
        return productService.updateProduct(productParamAdaptor.fromParam(productParam)).getId();
    }

    @CrossOrigin
    @RequestMapping(value = "{productId}",method = RequestMethod.DELETE)
    public Boolean removeProduct(@PathVariable("productId") Long productId) {
        return productService.removeProduct(productId);
    }

}
