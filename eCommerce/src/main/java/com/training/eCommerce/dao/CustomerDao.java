package com.training.eCommerce.dao;

import java.util.List;

import com.training.eCommerce.entity.Customer;

public interface CustomerDao {

	List<Customer> findById(Integer customerId);

}
