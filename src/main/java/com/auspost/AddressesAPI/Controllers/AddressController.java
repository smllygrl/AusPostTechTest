package com.auspost.AddressesAPI.Controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.auspost.AddressesAPI.DataTransferObjects.AddressDTO;
import com.auspost.AddressesAPI.Entities.Address;
import com.auspost.AddressesAPI.Services.AddressService;

@RestController
@RequestMapping(value = "/address")
public class AddressController {
	
	@Autowired
	AddressService addressService;
	
	@GetMapping
	public List<Address> getAddresses() {
		// what services to call dep on params
		return addressService.all();
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public void saveAddress(@Valid @RequestBody AddressDTO address) {
		addressService.create(address);
	}
	

}
