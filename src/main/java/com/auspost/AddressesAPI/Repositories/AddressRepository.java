package com.auspost.AddressesAPI.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.auspost.AddressesAPI.Entities.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
	
	// GET /addresses/postcodes?suburb={suburb}
	// Returns the postcode of a suburb
	@Query(value = "SELECT postcodes FROM address WHERE suburbs = :suburb", nativeQuery = true)
	Integer postcodeBySuburb(@Param("suburb") String suburb);
	
	// GET /addresses/suburbs?postcode={postcode}
	// Returns the suburb(s) of a postcode
	@Query(value = "SELECT suburbs FROM address WHERE postcodes = :postcode", nativeQuery = true)
	List<String> suburbsByPostcode(@Param("postcode")Integer postcode);

}
  