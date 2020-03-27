package com.training.eCommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.training.eCommerce.dao.CustomerDao;
import com.training.eCommerce.dao.VendorDao;
import com.training.eCommerce.dto.RatingDTO;
import com.training.eCommerce.dto.VendorDTO;
import com.training.eCommerce.dto.VendorRatingCreationRequest;
import com.training.eCommerce.entity.Customer;

@Service
@Transactional
public class VendorService {
	
	@Autowired
	VendorDao vendorDao;
	
	@Autowired
	CustomerDao customerDao;
	
	
	public List<VendorDTO> getVendors(Integer id) {
		List<VendorDTO> vendorList = vendorDao.findById(id);
		if(vendorList.isEmpty()) {
			return vendorList;
		}
		
		
		vendorList = vendorDao.getRatingDetails(vendorList);
		
		vendorList.sort((c1, c2) -> c2.getAverageRating().compareTo(c1.getAverageRating()));
		
		return vendorList;
	}


	public List<RatingDTO> getRatings(Integer vendorId) {
		List<RatingDTO> ratingsList = vendorDao.getRatings(vendorId);
		ratingsList.sort((c1, c2) -> c2.getRating().compareTo(c1.getRating()));
		return ratingsList;
	}


	public Long createRating(VendorRatingCreationRequest vendorRatingCreationRequest) {
		List<Customer> customer = customerDao.findById(vendorRatingCreationRequest.getCustomerId());
		if (!customer.isEmpty()) {
			return vendorDao.createRating(vendorRatingCreationRequest, customer.get(0).getName());
		} else {
			return 0L;
		}
	}

}
