package com.mapeo.restjpa2;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mapeo.restjpa2.entity.Companias;
import com.mapeo.restjpa2.repository.CompaniaRepository;
import com.mapeo.restjpa2.service.CatalogosService;

@SpringBootTest
 class CompaniaServiceTest {


	private static final Log LOG =  LogFactory.getLog(CompaniaServiceTest.class);
	
	@Autowired
	CompaniaRepository companiaRepo;
	
	@Autowired
	CatalogosService catalogoService;
	
	
	@Test
	void getCompanias(){
		
		List<Companias> listaCompanias =  companiaRepo.findAll();
		
		LOG.info("TEST getCompanias: COMPLETADO CON EXITO");
		assertTrue(listaCompanias.size()>0,"PRUEBA UNITARA EXITOSA, getCompanias ");
	}
	
	@Test
	void guardar() {


		Companias companias =  new Companias();
		companias.setClaseVia("N/A");
		companias.setCodPostal(3005);
		companias.setNombreCompania("Universales");
		companias.setNombreVia("N/A");
		companias.setNotas("Ninguna");
		companias.setNumeroVia(344);
		companias.setTelefonoContratacion(23230500);
		companias.setTelefonoSiniestros(23230500);
		
		Companias resultado =  companiaRepo.save(companias);
		

		LOG.info("TEST guardar: COMPLETADO CON EXITO");
		assertNotNull(resultado,"PRUEBA UNITARIA EXITOSA, guardar nueva compania");
	}
	
	@Test
	void eliminarCompania() {
		
		try {
			Optional<Companias> busqueda =  companiaRepo.findById("Universales");
			if(busqueda.isPresent()) {
				companiaRepo.delete(busqueda.get());
			}

			LOG.info("TEST eliminarCompania: COMPLETADO CON EXITO");
			assert(true);
			
		} catch (Exception e) {
			LOG.error("TEST eliminarCompania: NO SE COMPLETO.  ERROR: " + e.getMessage() );
			fail("Dio error: " + e.getMessage());
		}
		
	}
	
	@Test 
	void getConDosParametros() {
		
		List<Companias> listaCompanias = companiaRepo.findByCodPostalGreaterThanAndNombreCompaniaLike(3000,"GUATEX2");
		
		LOG.info("TEST findByCodPostalGreaterThanAndNombreCompaniaLike: COMPLETADO CON EXITO");
		assertTrue(listaCompanias.size()>0,"PRUEBA UNITARA EXITOSA, getConDosParametros ");
		
	}
	
	@Test
	void getCompaniaTerminadaEn() {
		List<Companias> listaCompanias = companiaRepo.findByNombreCompaniaEndingWithOrNombreCompaniaContaining("X","GUA");
		
		LOG.info("TEST findByNombreCompaniaEndingWithOrNombreCompaniaContaining: COMPLETADO CON EXITO");
		assertTrue(listaCompanias.size()>0,"PRUEBA UNITARA EXITOSA, getCompaniaTerminadaEn ");
	}
	
	@Test 
	void getSegurosPorCompania(){
		
		List<Map<String, Object>> listaCompaniasQuery =  catalogoService.segurosPorCompania("GUATEX2");
		
		LOG.info("TEST segurosPorCompania: COMPLETADO CON EXITO");
		assertTrue(listaCompaniasQuery.size()>0,"PRUEBA UNITARA EXITOSA, getSegurosPorCompania ");
	}
	
}
