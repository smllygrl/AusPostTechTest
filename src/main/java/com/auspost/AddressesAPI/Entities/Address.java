package com.auspost.AddressesAPI.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Integer postcode;
	private String suburbs;
	
	public Address(Integer postcode, String suburbs) {
		this.postcode = postcode;
		this.suburbs = suburbs;
	}
	
	public Address() {};
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
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
