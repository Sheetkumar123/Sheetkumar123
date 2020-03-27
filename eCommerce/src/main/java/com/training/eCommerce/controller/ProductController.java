package com.training.eCommerce.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.training.eCommerce.dto.ProductRatingCreationRequest;
import com.training.eCommerce.dto.ProductRatingsResponseDTO;
import com.training.eCommerce.dto.ProductResponseDTO;
import com.training.eCommerce.dto.RatingCreationResponseDTO;
import com.training.eCommerce.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@GetMapping(value="")
	public ResponseEntity searchProducts(@RequestParam(value = "productName") Optional<String> productName,
            @RequestParam("categoryName") Optional<String> categoryName) {
		
		ProductResponseDTO productResponseDTO = new ProductResponseDTO();
		if(productName.isPresent()) {
			productResponseDTO.setStatusCode(600);
			productResponseDTO.setMessage("Product details fetched successfully");
			productResponseDTO.setProductList(productService.getProductsByProductName(productName.get()));
		}
		else if(categoryName.isPresent()) {
			productResponseDTO.setStatusCode(600);
			productResponseDTO.setMessage("Product details fetched successfully");
			productResponseDTO.setProductList(productService.getProductsByCategoryName(categoryName.get()));
		}
		else {
			productResponseDTO.setStatusCode(600);
			productResponseDTO.setMessage("Product details fetched successfully");
			productResponseDTO.setProductList(productService.getProduts());
		}
		return ResponseEntity.status(HttpStatus.OK).body(productResponseDTO);
	}
	
	
	@GetMapping(value="/{id}")
	public ResponseEntity getProduct(@PathVariable("id") Integer id) {
		
		ProductResponseDTO productResponseDTO = new ProductResponseDTO();
		productResponseDTO.setStatusCode(600);
		productResponseDTO.setMessage("Product details fetched successfully");
		productResponseDTO.setProductList(productService.getProduts());
		return ResponseEntity.status(HttpStatus.OK).body(productResponseDTO);		
	}
	
	@GetMapping(value="/ratings/{productId}")
	public ResponseEntity getRatings(@PathVariable("productId") Integer productId) {
		
		ProductRatingsResponseDTO productRatingsResponseDTO = new ProductRatingsResponseDTO();
		productRatingsResponseDTO.setStatusCode(600);
		productRatingsResponseDTO.setMessage("Product rating details fetched successfully");
		productRatingsResponseDTO.setRatingsList(productService.getRatings(productId));
		return ResponseEntity.status(HttpStatus.OK).body(productRatingsResponseDTO);		
	}
	
	@PostMapping(value="/ratings")
	public ResponseEntity createRatings(@RequestBody ProductRatingCreationRequest productRatingCreationRequest) {
		RatingCreationResponseDTO ratingCreationResponseDTO = new RatingCreationResponseDTO();
		Long ratingId = productService.createRating(productRatingCreationRequest);
		if (ratingId > 0L) {
			ratingCreationResponseDTO.setStatusCode(600);
			ratingCreationResponseDTO.setMessage("Rating created successfully");
			ratingCreationResponseDTO.setRatingId(ratingId);
		} else {
			ratingCreationResponseDTO.setStatusCode(601);
			ratingCreationResponseDTO.setMessage("Failed to create product rating resource.");
		}
		return ResponseEntity.status(HttpStatus.OK).body(ratingCreationResponseDTO);
	}

}
