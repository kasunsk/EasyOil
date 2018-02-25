package com.oilseller.oilbrocker.order.adaptor;

import com.oilseller.oilbrocker.order.dto.Customer;
import com.oilseller.oilbrocker.order.param.CustomerDetailsParam;
import com.oilseller.oilbrocker.platform.adaptor.AbstractParamAdaptor;
import com.oilseller.oilbrocker.platform.util.DateUtil;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class CustomerParamAdaptor extends AbstractParamAdaptor<CustomerDetailsParam, Customer> {

    public CustomerParamAdaptor() {
        super(CustomerDetailsParam.class, Customer.class);
    }

    @Override
    public CustomerDetailsParam fromDto(Customer customer) {
        CustomerDetailsParam customerDetailsParam = super.fromDto(customer);
        customerDetailsParam.setCreatedDate(DateUtil.toSimpleDate(customer.getCreatedDate()));
        customerDetailsParam.setLastModifiedDate(DateUtil.toSimpleDate(customer.getLastModifiedDate()));
        return customerDetailsParam;
    }

    @Override
    protected PropertyMap<CustomerDetailsParam, Customer> fromParamMappings() {
        return new PropertyMap<CustomerDetailsParam, Customer>() {
            @Override
            protected void configure() {
            }
        };
    }

    @Override
    protected PropertyMap<Customer, CustomerDetailsParam> fromDtoMappings() {
        return new PropertyMap<Customer, CustomerDetailsParam>() {
            @Override
            protected void configure() {
            }
        };
    }
}
