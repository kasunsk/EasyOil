package com.oilseller.oilbrocker.order.adaptor;

import com.oilseller.oilbrocker.order.dto.Customer;
import com.oilseller.oilbrocker.order.entity.CustomerEntity;
import com.oilseller.oilbrocker.platform.adaptor.AbstractModelAdaptor;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class CustomerModelAdaptor extends AbstractModelAdaptor<CustomerEntity, Customer> {

    public CustomerModelAdaptor() {
        super(CustomerEntity.class, Customer.class);
    }

    @Override
    protected PropertyMap<CustomerEntity, Customer> fromModelMappings() {
        return new PropertyMap<CustomerEntity, Customer>() {
            @Override
            protected void configure() {
            }
        };
    }

    @Override
    protected PropertyMap<Customer, CustomerEntity> fromDtoMappings() {
        return new PropertyMap<Customer, CustomerEntity>() {
            @Override
            protected void configure() {
            }
        };
    }
}
