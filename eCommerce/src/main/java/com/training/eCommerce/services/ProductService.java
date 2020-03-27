package com.training.eCommerce.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.training.eCommerce.dao.CustomerDao;
import com.training.eCommerce.dao.ProductDao;
import com.training.eCommerce.dto.ProductDTO;
import com.training.eCommerce.dto.ProductRatingCreationRequest;
import com.training.eCommerce.dto.RatingDTO;
import com.training.eCommerce.entity.Customer;

@Service
@Transactional
public class ProductService {
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private CustomerDao customerDao;

	public List<ProductDTO> getProductById(Integer id) {
		List<ProductDTO> productList = productDao.findById(id);
		if(productList.isEmpty()) {
			return productList;
		}
		
		return getAllDetails(productList);
	}

	public List<ProductDTO> getProductsByProductName(String productName) {
		List<ProductDTO> productList = productDao.findByName(productName);
		if(productList.isEmpty()) {
			return productList;
		}
		
		return getAllDetails(productList);
	}

	public List<ProductDTO> getProductsByCategoryName(String categoryName) {
		List<ProductDTO> productList = productDao.findByCategoryName(categoryName);
		if(productList.isEmpty()) {
			return productList;
		}
		
		return getAllDetails(productList);
	}

	public List<ProductDTO> getProduts() {
		List<ProductDTO> productList = productDao.findAll();
		if(productList.isEmpty()) {
			return productList;
		}
		
		return getAllDetails(productList);
	}
	
	private List<ProductDTO> getAllDetails(List<ProductDTO> productList) {
		productList = productDao.getProductsRatings(productList);
		
		productList = productDao.getVendorDetails(productList);
		
		productList = productDao.getVendorRatingDetails(productList);
		
		productList.sort((c1, c2) -> c2.getAverageRating().compareTo(c1.getAverageRating()));
		
		return productList;
	}

	public List<RatingDTO> getRatings(Integer productId) {
		List<RatingDTO> ratingsList = productDao.getRatings(productId);
		ratingsList.sort((c1, c2) -> c2.getRating().compareTo(c1.getRating()));
		return ratingsList;
	}

	public Long createRating(ProductRatingCreationRequest productRatingCreationRequest) {
		List<Customer> customer = customerDao.findById(productRatingCreationRequest.getCustomerId());
		if (!customer.isEmpty()) {
			return productDao.createRating(productRatingCreationRequest, customer.get(0).getName());
		} else {
			return 0L;
		} 
	}

}
