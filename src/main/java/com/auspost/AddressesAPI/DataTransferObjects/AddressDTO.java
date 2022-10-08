package com.auspost.AddressesAPI.DataTransferObjects;

import javax.validation.constraints.NotNull;

public class AddressDTO {
	
	@NotNull
	private Integer postcode;
	
	@NotNull
	private String suburb;
	
	public Integer getPostcode() {
		return postcode;
	}
	
	public void setPostcode(Integer postcode) {
		this.postcode = postcode;
	}
	
	public String getSuburb() {
		return suburb;
	}

	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}

}
