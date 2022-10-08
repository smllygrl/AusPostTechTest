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
	
	// GET /addresses
	public List<Address> all() {
		return addressRepository.findAll();
	}
	
	// TODO
	// Below must become secure/ private
	// Below must execute checks to ensure no duplicates of Postcode / Suburb
	// POST /addresses
	public void create(AddressDTO address) {
		Address newAddress = new Address(address.getPostcode(), address.getSuburb());
		addressRepository.save(newAddress);
	}
	
	// GET /addresses/postcodes?suburb={suburb}
		public Integer getPostcodeBySuburb(String suburbName) {
		Integer postcodeInt = this.addressRepository.postcodeBySuburb(suburbName);
		return postcodeInt;
	}
	
	// GET /addresses/suburbs?postcode={postcode}
		public List<String> getSuburbsByPostcode(Integer postcodeInt) {
		List<String> suburbsList = this.addressRepository.suburbsByPostcode(postcodeInt);
		return suburbsList;
	}
	
}
