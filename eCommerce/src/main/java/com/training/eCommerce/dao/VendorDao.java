package com.training.eCommerce.dao;

import java.util.List;

import com.training.eCommerce.dto.RatingDTO;
import com.training.eCommerce.dto.VendorDTO;
import com.training.eCommerce.dto.VendorRatingCreationRequest;

public interface VendorDao {

	List<VendorDTO> findById(Integer id);

	List<VendorDTO> getRatingDetails(List<VendorDTO> vendorList);

	List<RatingDTO> getRatings(Integer vendorId);

	Long createRating(VendorRatingCreationRequest vendorRatingCreationRequest, String name);

}
