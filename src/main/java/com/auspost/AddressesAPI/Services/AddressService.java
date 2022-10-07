package com.auspost.AddressesAPI.Services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.auspost.AddressesAPI.DataTransferObjects.AddressDTO;
import com.auspost.AddressesAPI.Entities.Address;
import com.auspost.AddressesAPI.Repositories.AddressRepository;

@Service
@Transactional
public class AddressService {
	
	@Autowired 
	AddressRepository addressRepository;
	
	public List<Address> all() {
		return addressRepository.findAll();
	}
	
	// TODO
	// Below must become secure/ private
	// Below must execute checks to ensure no duplicates of Postcode / Suburb
	public void create(AddressDTO address) {
		Address newAddress = new Address(address.getPostcode(), address.getSuburb());
		addressRepository.save(newAddress);
	}
	
//	@Query(value = "SELECT suburbs FROM addresses WHERE postcode = :postcodeInt")
//	public String getSuburbs(@Param("postcodeInt") Integer postcode) {
		// takes in a postcode
		// find id of postcode
		// return suburbs associated with id
//		return getSuburbs(postcode);
//	}
//	
//	public Integer getPostcode() {
//		// takes in a suburb
//		// find suburb in a table
//		// return postcode associated with suburb
//	}
//	
	

}
