package com.auspost.AddressesAPI.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.auspost.AddressesAPI.Entities.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{

}
  