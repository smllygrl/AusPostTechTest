package com.auspost.AddressesAPI.Controllers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ExtendWith(SpringExtension.class)
@WebMvcTest(HealthCheckController.class)
class HealthCheckControllerIntTest {
	
	@Autowired
	private MockMvc mockMvc;

	// NOTE
	// The @WithTestUser annotation logs in with the default values of "user" and "password".
	// These are the credentials used in security config.
	@Test
	@WithMockUser
	void test() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/");
		MvcResult result = mockMvc.perform(request).andReturn();
		assertEquals("Hello World!", result.getResponse().getContentAsString());
	}

}
