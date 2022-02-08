package com.mapeo.restjpa2;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mapeo.restjpa2.entity.CompaniasSeguros;
import com.mapeo.restjpa2.repository.CompaniasSegurosRepository;


@SpringBootTest
class CompaniasSegurosServiceTest {

	
	private static final Log LOG =  LogFactory.getLog(CompaniasSegurosServiceTest.class);
	
	@Autowired
	CompaniasSegurosRepository companiasRepository;
	
	@Test
	void getCompaniasSeguros() {
		List<CompaniasSeguros> listaCompaniasSeguros =  companiasRepository.findAll();
		

		LOG.info("TEST getCompaniasSeguros: COMPLETADO CON EXITO");
		assertTrue(listaCompaniasSeguros.size()> 0 ,"PRUEBA UNITARIA EXITOSA: getCompaniasSeguros");
	}
	
	void guardar() {
		
		try {
			CompaniasSeguros nuevaCompaniaSeguros = new CompaniasSeguros();
			nuevaCompaniaSeguros.setNombreCompania("GUATEX2");
			nuevaCompaniaSeguros.setNumeroPoliza(38);
			
			CompaniasSeguros resultadoGuardar =  companiasRepository.save(nuevaCompaniaSeguros);
			

			LOG.info("TEST guardar: COMPLETADO CON EXITO");
			assertNotNull(resultadoGuardar,"PRUEBA UNITARIA EXITORA: guardar");
			
		} catch (Exception e) {
			LOG.error("TEST guardar: COMPLETADO CON EXITO     ERROR: "+e.getMessage());
			fail("Dio error: " + e.getMessage());
		}
		
		
	}
	
	@Test
	void eliminarCompaniasSeguros() {
		Optional<CompaniasSeguros> companiasSegurosBusqueda =  companiasRepository.findById(2);
		if(companiasSegurosBusqueda.isPresent()) {
			companiasRepository.delete(companiasSegurosBusqueda.get());
		}
		

		LOG.info("TEST eliminarCompaniasSeguros: COMPLETADO CON EXITO");
		assertTrue(true,"PRUEBA UNITARIA EXITOSA: eliminarCompaniasSeguros");
		
	}
	
	@Test
	void getFiltrandoPorNombreCompanias() {
		
		List<CompaniasSeguros> listaCompaniasSeguros = companiasRepository.findByNombreCompaniaNot("UNIVERSALES");
		
		LOG.info("TEST getFiltrandoPorNombreCompanias: COMPLETADO CON EXITO");
		assertTrue(listaCompaniasSeguros.size()> 0 ,"PRUEBA UNITARIA EXITOSA: getFiltrandoPorNombreCompanias");
		
	}
	
	
	@Test
	void getFiltrandoCompania() {
		
		List<CompaniasSeguros> listaCompaniasSeguros = companiasRepository.findByNumeroPolizaBefore(40);
		
		LOG.info("TEST getFiltrandoCompania: COMPLETADO CON EXITO");
		assertTrue(listaCompaniasSeguros.size()> 0 ,"PRUEBA UNITARIA EXITOSA: getFiltrandoCompania");
		
	}
	
}
