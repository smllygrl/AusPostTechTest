package com.auspost.AddressesAPI.Controllers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HealthCheckControllerTest {

	// Below is a UNIT TEST
	@Test
	void test() {
		HealthCheckController healthCheckController = new HealthCheckController();
		String responseString = healthCheckController.test();
		assertEquals("Hello World!", responseString);
	}

}
