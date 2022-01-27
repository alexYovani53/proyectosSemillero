package com.mapeo.restjpa2;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mapeo.restjpa2.service.ProcedimientoAlmacenado;
import com.mapeo.entity.entity.Seguros;
import com.mapeo.restjpa2.dto.ProcedimientoInsertPolizaDto;
import com.mapeo.restjpa2.dto.SegurosDto;


@SpringBootTest
 class ProcedimientoServiceTest {

	private static Log LOG = LogFactory.getLog(ProcedimientoServiceTest.class);
	
	@Autowired 
	ProcedimientoAlmacenado procAlmacenado;
	
	@Test
	void eliminarCliente() {
		int resultadoEliminacion = procAlmacenado.eliminar(3);
		
		LOG.info("TEST eliminarCliente usando procedimientos: COMPLETADO CON EXITO");
		assertNotEquals(0, resultadoEliminacion,"PRUEBA UNITARA EXITOSA: eliminarCliente");
		
	}
	
	@Test
    @DisplayName("ProcedimientoPoliza")
	void ProcedimientoInsertPolizaDto() {
		
		Seguros seguroPivote = new Seguros();
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
		seguro.setNumeroPoliza(seguroPivote.getNumeroPoliza());
		seguro.setRamo("vida");
		
		ProcedimientoInsertPolizaDto resultado = procAlmacenado.insertarPolizaConDosSalidas(seguro);
		
		LOG.info("TEST ProcedimientoInsertPolizaDto: COMPLETADO CON EXITO");
		assertNotNull(resultado,"PRUEBA UNITARA EXITOSA: ProcedimientoInsertPolizaDto");
	}
	
}
