package com.auspost.AddressesAPI.DataTransferObjects;

import javax.validation.constraints.NotNull;

public class AddressDTO {
	
	@NotNull
	private Integer postcode;
	
	@NotNull
	private String suburbs;
	
	public Integer getPostcode() {
		return postcode;
	}
	
	public void setPostcode(Integer postcode) {
		this.postcode = postcode;
	}
	
	public String getSuburbs() {
		return suburbs;
	}
	
	public void setSuburbs(String suburbs) {
		this.suburbs = suburbs;
	}
	
	

}
