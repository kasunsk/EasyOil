package com.oilseller.oilbrocker.order.service.impl;

import com.oilseller.oilbrocker.order.adaptor.CustomerModelAdaptor;
import com.oilseller.oilbrocker.order.dao.CustomerDao;
import com.oilseller.oilbrocker.order.dto.Customer;
import com.oilseller.oilbrocker.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

    private CustomerDao customerDao;
    private CustomerModelAdaptor customerModelAdaptor = new CustomerModelAdaptor();

    @Autowired
    public OrderServiceImpl(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Transactional
    @Override
    public Long addCustomerDetails(Customer customer) {
        validateCustomer(customer);
        return customerDao.addCustomer(customerModelAdaptor.fromDto(customer));
    }

    private void validateCustomer(Customer customer) {

    }
}
