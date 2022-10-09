package com.auspost.AddressesAPI.Controllers;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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
	
	// NOTE
	// Given more time I would explore further what Principal is and whether it is actually necesary here
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public void saveAddress(@Valid @RequestBody AddressDTO address, Principal principal) {
		// TODO
		// Add a check to ensure there is not already a suburb of the same name in the DB
		addressService.create(address);
	}
	
	// GET /addresses/postcodes?suburb={suburb}
	@PreAuthorize("permitAll()")
	@GetMapping(value = "/postcodes")
	public Integer getPostcodeOfSuburb(@Valid @RequestParam(value = "suburb") String suburbName){
		return addressService.getPostcodeBySuburb(suburbName);
	}
	
	// GET /addresses/suburbs?postcode={postcode}
	@GetMapping(value = "/suburbs")
	@PreAuthorize("permitAll()")
	public List<String> getSuburbsOfPostcode(@Valid @RequestParam(value = "postcode") Integer postcodeInt){
		return addressService.getSuburbsByPostcode(postcodeInt);
	}

}
