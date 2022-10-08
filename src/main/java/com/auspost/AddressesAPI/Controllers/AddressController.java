package com.auspost.AddressesAPI.Controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.auspost.AddressesAPI.DataTransferObjects.AddressDTO;
import com.auspost.AddressesAPI.Entities.Address;
import com.auspost.AddressesAPI.Services.AddressService;

@RestController
@RequestMapping(value = "/addresses")
public class AddressController {
	
	@Autowired
	AddressService addressService;
	
	// GET /addresses
	@GetMapping
	public List<Address> getAddresses() {
		return addressService.all();
	}
	
	// POST /addresses
	// TODO
	// Ensure below is a secure endpoint
	// Do a check to see if combination already exists
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public void saveAddress(@Valid @RequestBody AddressDTO address) {
		addressService.create(address);
	}
	
	// GET /addresses/postcodes?suburb={suburb}
	@GetMapping(value = "/postcodes")
	public Integer getPostcodeOfSuburb(@Valid @RequestParam(value = "suburb") String suburbName){
		return addressService.getPostcodeBySuburb(suburbName);
	}
	
	// GET /addresses/suburbs?postcode={postcode}
	@GetMapping(value = "/suburbs")
	public List<String> getSuburbsOfPostcode(@Valid @RequestParam(value = "postcode") Integer postcodeInt){
		return addressService.getSuburbsByPostcode(postcodeInt);
	}

}
