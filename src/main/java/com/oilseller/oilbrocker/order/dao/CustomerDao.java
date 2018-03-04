package com.oilseller.oilbrocker.order.dao;

import com.oilseller.oilbrocker.order.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("customerDao")
public interface CustomerDao extends JpaRepository<CustomerEntity, Long> {

//    Long addCustomer(CustomerEntity customerEntity);
//
//    CustomerEntity loadCustomerById(Long customerId);
}
