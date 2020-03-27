package com.training.eCommerce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.training.eCommerce.dto.ProductDTO;
import com.training.eCommerce.dto.ProductRatingCreationRequest;
import com.training.eCommerce.dto.RatingDTO;
import com.training.eCommerce.dto.VendorDTO;

@Repository
public class ProductDaoImpl implements ProductDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<ProductDTO> getVendorRatingDetails(List<ProductDTO> productList) {
		for(ProductDTO product: productList) {
			for(VendorDTO vendorDTO : product.getVendorList()) {
				String sql = "select * from vendors_ratings where vendor_id = ?";
				
				vendorDTO.setVendorRatings(jdbcTemplate.query(sql, new Object[] {vendorDTO.getId()}, (resultset, i) -> {
					
					RatingDTO ratingDTO = new RatingDTO();
					ratingDTO.setRatingId(resultset.getInt("id"));
					ratingDTO.setRating(resultset.getInt("rating"));
					ratingDTO.setComment(resultset.getString("comment"));
					ratingDTO.setCustomerId(resultset.getInt("customer_id"));
					ratingDTO.setCustomerName(resultset.getString("created_by"));
					if(resultset.getString("modified_on") != null) {
						ratingDTO.setRatedDate(resultset.getString("modified_on"));
					} else {
						ratingDTO.setRatedDate(resultset.getString("created_on"));
					}
					return ratingDTO;
					}));
				
				sql = "select * from vendors_ratings where vendor_id = ?";
				vendorDTO.setAverageRating(jdbcTemplate.query(sql, new Object[] {vendorDTO.getId()}, (resultset, i) -> { 
					return resultset.getInt("rating");
					}).stream().mapToInt(val -> val).average().orElse(0.0));
			}
		}
		
		return productList;
	}

	public List<ProductDTO> getVendorDetails(List<ProductDTO> productList) {
		for(ProductDTO product : productList) {
			String sql = "select i.id as id, i.vendor_id as vendor_id, i.price as price, i.quantity as quantity, v.id as vId, v.name as name, v.vendor_code as code, "
					+ "v.location as location, v.address as address, v.created_by as vendorCreatedBy, v.created_on as vendorCreatedOn, v.modified_by as vendorModifiedBy, v.modified_on as vendorModifiedOn"
					+ " from inventory i, vendors v where i.product_id = ? and i.vendor_id = v.id";
			
			product.setVendorList(jdbcTemplate.query(sql, new Object[] {product.getProductId()}, (resultset, i) -> {
				
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
			}));
			
			sql = "select * from inventory where product_id = ?";
			product.setQuantity(jdbcTemplate.query(sql, new Object[] {product.getProductId()}, (resultset, i) -> {
				return resultset.getInt("quantity");
			}).stream().reduce(0, (a, b) -> a + b));
			
		}
		
		return productList;
	}

	public List<ProductDTO> getProductsRatings(List<ProductDTO> productList) {
		
		for(ProductDTO product : productList) {
			String sql = "select * from products_ratings where product_id = ?";
			
			product.setProductRatings(jdbcTemplate.query(sql, new Object[] {product.getProductId()}, (resultset, i) -> {
				
				RatingDTO ratingDTO = new RatingDTO();
				ratingDTO.setRatingId(resultset.getInt("id"));
				ratingDTO.setRating(resultset.getInt("rating"));
				ratingDTO.setComment(resultset.getString("comment"));
				ratingDTO.setCustomerId(resultset.getInt("customer_id"));
				ratingDTO.setCustomerName(resultset.getString("created_by"));
				if(resultset.getString("modified_on") != null) {
					ratingDTO.setRatedDate(resultset.getString("modified_on"));
				} else {
					ratingDTO.setRatedDate(resultset.getString("created_on"));
				}
				return ratingDTO;
				}));

			sql = "select * from products_ratings where product_id = ?";
			product.setAverageRating(jdbcTemplate.query(sql, new Object[] {product.getProductId()}, (resultset, i) -> { 
				return resultset.getInt("rating");
				}).stream().mapToInt(val -> val).average().orElse(0.0));
		}
		return productList;
	}

	public List<ProductDTO> findAll() {
		String sql = "select p.id as id, p.name as name, p.product_code as product_code, p.category_id as categoryId, p.description as description, "
				+ "p.created_by as productCreatedBy, p.created_on as productCreatedOn, p.modified_by as productModifiedBy, p.modified_on as productModifiedOn, "
				+ "cat.id as catId, cat.name as catName, cat.category_code as catCode from products p, categories cat where cat.id = p.category_id";
		
		return jdbcTemplate.query(sql, (resultset, i) -> { return getProductDetails(resultset); });
	}

	private ProductDTO getProductDetails(ResultSet resultset) throws SQLException {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setProductId(resultset.getInt("id"));
		productDTO.setProductName(resultset.getString("name"));
		productDTO.setProductCode(resultset.getString("product_code"));
		productDTO.setDescription(resultset.getString("description"));
		productDTO.setCategoryId(resultset.getInt("categoryId"));
		productDTO.setCategoryName(resultset.getString("catName"));
		productDTO.setCategoryCode(resultset.getString("catCode"));
		productDTO.setCreatedBy(resultset.getString("productCreatedBy"));
		productDTO.setCreatedOn(resultset.getString("productCreatedOn"));
		productDTO.setModifiedBy(resultset.getString("productModifiedBy"));
		productDTO.setModifiedOn(resultset.getString("productModifiedOn"));
		
		return productDTO;
	}

	@Override
	public List<ProductDTO> findByName(String productName) {
		String sql = "select p.id as id, p.name as name, p.product_code as product_code, p.category_id as categoryId, p.description as description, "
				+ "p.created_by as productCreatedBy, p.created_on as productCreatedOn, p.modified_by as productModifiedBy, p.modified_on as productModifiedOn, "
				+ "cat.id as catId, cat.name as catName, cat.category_code as catCode from products p, categories cat where cat.id = p.category_id and p.name = ?";
		
		return jdbcTemplate.query(sql, new Object[] {productName}, (resultset, i) -> { return getProductDetails(resultset); });
	}

	@Override
	public List<ProductDTO> findByCategoryName(String categoryName) {
		String sql = "select p.id as id, p.name as name, p.product_code as product_code, p.category_id as categoryId, p.description as description, "
				+ "p.created_by as productCreatedBy, p.created_on as productCreatedOn, p.modified_by as productModifiedBy, p.modified_on as productModifiedOn, "
				+ "cat.id as catId, cat.name as catName, cat.category_code as catCode from products p, categories cat where cat.id = p.category_id and cat.name = ?";
		
		return jdbcTemplate.query(sql, new Object[] {categoryName}, (resultset, i) -> { return getProductDetails(resultset); });
	}

	@Override
	public List<ProductDTO> findById(Integer id) {
		String sql = "select p.id as id, p.name as name, p.product_code as product_code, p.category_id as categoryId, p.description as description, "
				+ "p.created_by as productCreatedBy, p.created_on as productCreatedOn, p.modified_by as productModifiedBy, p.modified_on as productModifiedOn, "
				+ "cat.id as catId, cat.name as catName, cat.category_code as catCode from products p, categories cat where cat.id = p.category_id and p.id = ?";
		
		return jdbcTemplate.query(sql, new Object[] {id}, (resultset, i) -> { return getProductDetails(resultset); });
	}

	@Override
	public List<RatingDTO> getRatings(Integer productId) {
		String sql = "select * from products_ratings where product_id = ?";
		
		return jdbcTemplate.query(sql, new Object[] {productId}, (resultset, i) -> {
			
			RatingDTO ratingDTO = new RatingDTO();
			ratingDTO.setRatingId(resultset.getInt("id"));
			ratingDTO.setRating(resultset.getInt("rating"));
			ratingDTO.setComment(resultset.getString("comment"));
			ratingDTO.setCustomerId(resultset.getInt("customer_id"));
			ratingDTO.setCustomerName(resultset.getString("created_by"));
			if(resultset.getString("modified_on") != null) {
				ratingDTO.setRatedDate(resultset.getString("modified_on"));
			} else {
				ratingDTO.setRatedDate(resultset.getString("created_on"));
			}
			return ratingDTO;
			});
	}

	@Override
	public Long createRating(ProductRatingCreationRequest request, String name) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		final String sql = "insert into products_ratings(rating, comment, product_id, customer_id, created_by, created_on, modified_on, modified_by) values(?, ?, ?, ?, ?, ?, ?, ?)"; 
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql, new String[] {"id"});
				ps.setInt(1, request.getRating());
				ps.setString(2, request.getComment());
				ps.setInt(3, request.getProductId());
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
