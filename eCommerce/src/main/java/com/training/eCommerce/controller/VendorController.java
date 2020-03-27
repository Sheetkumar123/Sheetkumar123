package com.training.eCommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.eCommerce.dto.ProductRatingCreationRequest;
import com.training.eCommerce.dto.RatingCreationResponseDTO;
import com.training.eCommerce.dto.VendorRatingCreationRequest;
import com.training.eCommerce.dto.VendorRatingsResponseDTO;
import com.training.eCommerce.dto.VendorResponseDTO;
import com.training.eCommerce.services.VendorService;

@RestController
@RequestMapping("/vendors")
public class VendorController {
	
	@Autowired
	VendorService vendorService;
	
	@GetMapping(value="/{id}")
	public ResponseEntity getProduct(@PathVariable("id") Integer id) {
		
		VendorResponseDTO vendorResponseDTO = new VendorResponseDTO();
		vendorResponseDTO.setStatusCode(600);
		vendorResponseDTO.setMessage("Vendor details fetched successfully");
		vendorResponseDTO.setVendorList(vendorService.getVendors(id));
		return ResponseEntity.status(HttpStatus.OK).body(vendorResponseDTO);		
	}
	
	@GetMapping(value="/ratings/{vendorId}")
	public ResponseEntity getRatings(@PathVariable("vendorId") Integer vendorId) {
		
		VendorRatingsResponseDTO vendorRatingsResponseDTO = new VendorRatingsResponseDTO();
		vendorRatingsResponseDTO.setStatusCode(600);
		vendorRatingsResponseDTO.setMessage("Vendor rating details fetched successfully");
		vendorRatingsResponseDTO.setRatingsList(vendorService.getRatings(vendorId));
		return ResponseEntity.status(HttpStatus.OK).body(vendorRatingsResponseDTO);		
	}
	
	@PostMapping(value="/ratings")
	public ResponseEntity createRatings(@RequestBody VendorRatingCreationRequest vendorRatingCreationRequest) {
		RatingCreationResponseDTO ratingCreationResponseDTO = new RatingCreationResponseDTO();
		Long ratingId = vendorService.createRating(vendorRatingCreationRequest);
		if ( ratingId > 0) {
			ratingCreationResponseDTO.setStatusCode(600);
			ratingCreationResponseDTO.setMessage("Rating created successfully");
			ratingCreationResponseDTO.setRatingId(ratingId);
		} else {
			ratingCreationResponseDTO.setStatusCode(601);
			ratingCreationResponseDTO.setMessage("Failed to create vendor rating resource.");
		}
		return ResponseEntity.status(HttpStatus.OK).body(ratingCreationResponseDTO);
	}
}
