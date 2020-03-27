package com.training.eCommerce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.training.eCommerce.dto.RatingDTO;
import com.training.eCommerce.dto.VendorDTO;
import com.training.eCommerce.dto.VendorRatingCreationRequest;

@Repository
public class VendorDaoImpl implements VendorDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<VendorDTO> findById(Integer id) {
		String sql = "select i.id as id, i.vendor_id as vendor_id, i.price as price, i.quantity as quantity, v.id as vId, v.name as name, v.vendor_code as code, "
				+ "v.location as location, v.address as address, v.created_by as vendorCreatedBy, v.created_on as vendorCreatedOn, v.modified_by as vendorModifiedBy, v.modified_on as vendorModifiedOn"
				+ " from inventory i, vendors v where i.vendor_id = ? and i.vendor_id = v.id";

		return jdbcTemplate.query(sql, new Object[] { id }, (resultset, i) -> {

			VendorDTO vendorDTO = new VendorDTO();
			vendorDTO.setId(resultset.getInt("vendor_id"));
			vendorDTO.setName(resultset.getString("name"));
			vendorDTO.setVendorCode(resultset.getString("code"));
			vendorDTO.setLocation(resultset.getString("location"));
			vendorDTO.setAddress(resultset.getString("address"));
			vendorDTO.setCreatedBy(resultset.getString("vendorCreatedBy"));
			vendorDTO.setCreatedOn(resultset.getString("vendorCreatedOn"));
			vendorDTO.setModifiedBy(resultset.getString("vendorModifiedBy"));
			vendorDTO.setModifiedOn(resultset.getString("vendorModifiedOn"));
			vendorDTO.setPrice(resultset.getDouble("price"));
			vendorDTO.setQuantity(resultset.getInt("quantity"));

			return vendorDTO;
		});
	}

	public List<VendorDTO> getRatingDetails(List<VendorDTO> vendorList) {
		for (VendorDTO vendorDTO : vendorList) {
			String sql = "select * from vendors_ratings where vendor_id = ?";

			vendorDTO.setVendorRatings(jdbcTemplate.query(sql, new Object[] { vendorDTO.getId() }, (resultset, i) -> {

				RatingDTO ratingDTO = new RatingDTO();
				ratingDTO.setRatingId(resultset.getInt("id"));
				ratingDTO.setRating(resultset.getInt("rating"));
				ratingDTO.setComment(resultset.getString("comment"));
				ratingDTO.setCustomerId(resultset.getInt("customer_id"));
				ratingDTO.setCustomerName(resultset.getString("created_by"));
				if (resultset.getString("modified_on") != null) {
					ratingDTO.setRatedDate(resultset.getString("modified_on"));
				} else {
					ratingDTO.setRatedDate(resultset.getString("created_on"));
				}
				return ratingDTO;
			}));

			sql = "select * from vendors_ratings where vendor_id = ?";
			vendorDTO.setAverageRating(jdbcTemplate.query(sql, new Object[] { vendorDTO.getId() }, (resultset, i) -> {
				return resultset.getInt("rating");
			}).stream().mapToInt(val -> val).average().orElse(0.0));
		}

		return vendorList;
	}

	@Override
	public List<RatingDTO> getRatings(Integer vendorId) {
		String sql = "select * from vendors_ratings where vendor_id = ?";

		return jdbcTemplate.query(sql, new Object[] { vendorId }, (resultset, i) -> {

			RatingDTO ratingDTO = new RatingDTO();
			ratingDTO.setRatingId(resultset.getInt("id"));
			ratingDTO.setRating(resultset.getInt("rating"));
			ratingDTO.setComment(resultset.getString("comment"));
			ratingDTO.setCustomerId(resultset.getInt("customer_id"));
			ratingDTO.setCustomerName(resultset.getString("created_by"));
			if (resultset.getString("modified_on") != null) {
				ratingDTO.setRatedDate(resultset.getString("modified_on"));
			} else {
				ratingDTO.setRatedDate(resultset.getString("created_on"));
			}
			return ratingDTO;
		});

	}

	@Override
	public Long createRating(VendorRatingCreationRequest request, String name) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		final String sql = "insert into vendors_ratings(rating, comment, vendor_id, customer_id, created_by, created_on, modified_on, modified_by) values(?, ?, ?, ?, ?, ?, ?, ?)"; 
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql, new String[] {"id"});
				ps.setInt(1, request.getRating());
				ps.setString(2, request.getComment());
				ps.setInt(3, request.getVendorId());
				ps.setInt(4, request.getCustomerId());
				ps.setString(5, name);
				ps.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
				ps.setString(7, null);
				ps.setString(8, null);
				return ps;
			}
		}, keyHolder);
		
		return (Long) keyHolder.getKey();
	}

}
