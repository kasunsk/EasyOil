package com.oilseller.oilbrocker.order.dao;

import com.oilseller.oilbrocker.order.entity.CustomerEntity;
import com.oilseller.oilbrocker.order.entity.OrderPlacementEntity;

public interface CustomerDao {

    Long addCustomer(CustomerEntity customerEntity);
}
