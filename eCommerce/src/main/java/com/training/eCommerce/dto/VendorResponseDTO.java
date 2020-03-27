package com.training.eCommerce.dto;

import java.util.List;

public class VendorResponseDTO {

	private int statusCode;

	private String message;

	private List<VendorDTO> vendorList;

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<VendorDTO> getVendorList() {
		return vendorList;
	}

	public void setVendorList(List<VendorDTO> vendorList) {
		this.vendorList = vendorList;
	}

}
