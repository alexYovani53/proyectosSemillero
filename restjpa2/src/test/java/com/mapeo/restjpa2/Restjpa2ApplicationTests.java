package com.mapeo.restjpa2;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Restjpa2ApplicationTests {

	@Test
	void contextLoads() {
		int prueba = 0;
		assertEquals(0, prueba,"SERVIDOR CORRIENDO CON EXITO");;
	}

}
