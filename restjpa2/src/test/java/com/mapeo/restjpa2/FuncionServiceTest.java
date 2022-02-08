package com.mapeo.restjpa2;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mapeo.restjpa2.entity.Seguros;
import com.mapeo.restjpa2.dto.SegurosDto;
import com.mapeo.restjpa2.service.Funciones;

@SpringBootTest
class FuncionServiceTest {


	private static final Log LOG =  LogFactory.getLog(FuncionServiceTest.class);
	
	
	@Autowired
	Funciones funciones;
	
	@Test
	void guardarSeguro() {
		
		Seguros seguroPivote =  new Seguros();
		SegurosDto seguro =  new SegurosDto();
		Date fecha;
		
		try {
			fecha = new SimpleDateFormat("yyyy-MM-dd").parse("10/10/2022");
		} catch (ParseException e) {
			fecha = new Date();
			LOG.error("ERROR AL PARSEAR UNA FECHA            ERROR: "+e.getMessage());
		}
		seguro.setCondicionesParticulares("ACCIDENTE");
		seguro.setDniCl(8);
		seguro.setFechaInicio(new Date());
		seguro.setFechaVencimiento(fecha);
		seguro.setNumeroPoliza(seguroPivote.getDniCl());
		seguro.setRamo("vida");
		
		int resultadoGuardarConFuncion = funciones.insertarPolizaConDosSalidas(seguro);
		

		LOG.info("TEST guardarSeguro: COMPLETADO CON EXITO");
		assertNotEquals(0, resultadoGuardarConFuncion,"PRUEBA UNITARA EXITOSA");
		
	}
}
