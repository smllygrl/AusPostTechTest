package com.auspost.AddressesAPI.Controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.BDDMockito.given;
import com.auspost.AddressesAPI.DataTransferObjects.AddressDTO;
import com.auspost.AddressesAPI.Repositories.AddressRepository;
import com.auspost.AddressesAPI.Services.AddressService;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

// Work I was following
// https://thepracticaldeveloper.com/guide-spring-boot-controller-tests/

// This test FAILS
// If I had more time I would correct this issue

@ExtendWith(MockitoExtension.class)
class AddressControllerIntTest {
	
	private MockMvc mockMvc;
	
	@Mock
	private AddressService addressService;
	
	@InjectMocks AddressController addressController;
	
	private JacksonTester<AddressDTO> address;
	
	@BeforeEach
	public void setup() {
		JacksonTester.initFields(this, new ObjectMapper());
		mockMvc = MockMvcBuilders.standaloneSetup(addressController).build();
	}

	@Test
	public void test() throws Exception {
		given(addressService.getPostcodeBySuburb("Reservoir")).willReturn(3073);
		
		MockHttpServletResponse response = mockMvc.perform(get("addresses/postcodes?suburb=Reservoir")
				.accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
		
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getContentAsString()).isEqualTo("3073");
		
	}

}
