package com.training.eCommerce.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.training.eCommerce.entity.Customer;

@Repository
public class CustomerDaoImpl implements CustomerDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Customer> findById(Integer customerId) {
		String sql = "select * from customers where id = ?";
		return jdbcTemplate.query(sql, new Object[] {customerId}, (resultset, i) -> {
			Customer customers = new Customer();
			customers.setId(resultset.getInt("id"));
			customers.setName(resultset.getString("name"));
			return customers;
		});		
	}

}
