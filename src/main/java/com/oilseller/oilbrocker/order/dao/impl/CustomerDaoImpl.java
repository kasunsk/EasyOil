package com.oilseller.oilbrocker.order.dao.impl;

import com.oilseller.oilbrocker.order.dao.CustomerDao;
import com.oilseller.oilbrocker.order.dto.Customer;
import com.oilseller.oilbrocker.order.entity.CustomerEntity;
import com.oilseller.oilbrocker.order.entity.OrderPlacementEntity;
import com.oilseller.oilbrocker.platform.dao.AbstractHibernateDao;
import org.springframework.stereotype.Repository;

@Repository("customerDao")
public class CustomerDaoImpl extends AbstractHibernateDao implements CustomerDao {

    @Override
    public Long addCustomer(CustomerEntity customerEntity) {
        getSession().save(customerEntity);
        return customerEntity.getCustomerId();
    }

    @Override
    public CustomerEntity loadCustomerById(Long customerId) {
        return getSession().get(CustomerEntity.class, customerId);
    }
}
