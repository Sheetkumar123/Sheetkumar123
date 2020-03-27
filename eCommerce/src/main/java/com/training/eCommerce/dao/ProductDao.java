package com.training.eCommerce.dao;

import java.util.List;

import com.training.eCommerce.dto.ProductDTO;
import com.training.eCommerce.dto.ProductRatingCreationRequest;
import com.training.eCommerce.dto.RatingDTO;

public interface ProductDao {

	List<ProductDTO> findAll();

	List<ProductDTO> getProductsRatings(List<ProductDTO> productList);

	List<ProductDTO> getVendorDetails(List<ProductDTO> productList);

	List<ProductDTO> getVendorRatingDetails(List<ProductDTO> productList);

	List<ProductDTO> findByName(String productName);

	List<ProductDTO> findByCategoryName(String categoryName);

	List<ProductDTO> findById(Integer id);

	List<RatingDTO> getRatings(Integer productId);

	Long createRating(ProductRatingCreationRequest productRatingCreationRequest, String name);

}
