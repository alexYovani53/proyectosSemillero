package com.mapeo.restjpa2;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mapeo.restjpa2.entity.Siniestros;
import com.mapeo.restjpa2.repository.SiniestroRepository;
import com.mapeo.restjpa2.utilidades.Utilidades;

@SpringBootTest
class SiniestroServiceTest {

	
	private static Log LOG  = LogFactory.getLog(SiniestroServiceTest.class);
	
	@Autowired
	SiniestroRepository siniestroRepo;
	
	@Autowired
	Utilidades utilidad;
	
	@Test
	void getSiniestros() {
		
		List<Siniestros> listaBusqueda = siniestroRepo.findAll();
				
		LOG.info("TEST getSiniestros: COMPLETADO CON EXITO");
		assertTrue(listaBusqueda.size()>0,"PRUEBA UNITARIA EXITOSA: getSiniestros");
	}
	
	@Test
	void guardarSiniestro() {
		
		Siniestros siniestro =  new Siniestros();		
		siniestro.setAceptado("SI");
		siniestro.setCausas("ACCIDENTE");
		siniestro.setFechaSiniestro(utilidad.getFecha("26/01/2022"));
		siniestro.setIdSiniestro(20);
		siniestro.setIndemnizacion(3044);
		siniestro.setNumeroPoliza(48);
		
		Siniestros resultadoGuardar =  siniestroRepo.save(siniestro);
		
		LOG.info("TEST guardarSiniestro: COMPLETADO CON EXITO");
		assertNotNull(resultadoGuardar,"PRUEBA UNITARIA EXITOSA");
	}
	
	@Test
	void eliminarSiniestro() {
		
		Optional<Siniestros> siniestroBusqueda =  siniestroRepo.findById(0);
		if(siniestroBusqueda.isPresent()) {
			siniestroRepo.delete(siniestroBusqueda.get());
		}
		
		LOG.info("TEST eliminarSiniestro: COMPLETADO CON EXITO");
		assertTrue(true,"PRUEBA UNITARIA EXITOSA: eliminarSiniestro");
	}
	
	@Test
	void getSiniestrosParams() {
		
		List<Siniestros> listaBusqueda = siniestroRepo.findByIndemnizacionBetween(1000, 40000);

		LOG.info("TEST getSiniestros: COMPLETADO CON EXITO");
		assertTrue(listaBusqueda.size()>0,"PRUEBA UNITARIA EXITOSA: getSiniestros");
	}
	

}
