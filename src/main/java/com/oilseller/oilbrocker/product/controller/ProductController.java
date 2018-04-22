package com.oilseller.oilbrocker.product.controller;

import com.oilseller.oilbrocker.product.adaptor.ProductParamAdaptor;
import com.oilseller.oilbrocker.product.param.ProductParam;
import com.oilseller.oilbrocker.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @RequestMapping(method = RequestMethod.POST)
    public Long addProductItem(@RequestBody ProductParam productParam) {
        return productService.addProduct(productParamAdaptor.fromParam(productParam));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ProductParam updateProduct(@RequestBody ProductParam productParam) {
        return productParamAdaptor.fromDto(productService.updateProduct(productParamAdaptor.fromParam(productParam)));
    }

}
