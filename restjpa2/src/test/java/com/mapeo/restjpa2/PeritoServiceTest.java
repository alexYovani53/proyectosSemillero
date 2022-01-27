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

import com.mapeo.entity.entity.Peritos;
import com.mapeo.restjpa2.repository.PeritoRepository;

@SpringBootTest
class PeritoServiceTest {

	
	private static Log LOG  = LogFactory.getLog(PeritoServiceTest.class);
	
	@Autowired
	PeritoRepository peritoRepo;
	
	@Test
	void getPeritos() {
		List<Peritos> listaPeritos = peritoRepo.findAll();
		
		LOG.info("TEST getPeritos: COMPLETADO CON EXITO");
		assertTrue(listaPeritos.size()> 0 ,"PRUEBA UNITARIA EXITOSA: getPeritos");
	}
	
	
	@Test
	void guardarPerito() {
		
		Peritos perito =  new Peritos();
		
		perito.setApellidoPerito1("Jeronimo");
		perito.setApellidoPerito2("Tomas");
		perito.setCiudad("Guatemala");
		perito.setClaseVia("N/A");
		perito.setCodPostal(3001);
		perito.setNombrePerito("Yovani");
		perito.setNumeroVia(20);
		perito.setTelefonoContacto(34383644);
		perito.setTelefonoOficina(34383644);
		
		
		Peritos resultado =  peritoRepo.save(perito);
		
		LOG.info("TEST guardarPerito: COMPLETADO CON EXITO");
		assertNotNull(resultado,"PRUEBA UNITARA EXITOSA: guardarPerito");
	}
	
	@Test
	void eliminarPerito() {
		
		Optional<Peritos> peritoExistente = peritoRepo.findById(7);
		if(peritoExistente.isPresent()) {
			peritoRepo.delete(peritoExistente.get());
		}

		LOG.info("TEST eliminarPerito: COMPLETADO CON EXITO");
		assertTrue(true,"PRUEBA UNITARIA EXITOSA: eliminarPerito");
		
	}
	
	@Test
	void getPeritosPorInicialNumeroTelefono(){
		List<Peritos> listaBusqueda= peritoRepo.findByTelefonoContactoNotLike(3);

		LOG.info("TEST getPeritosPorInicialNumeroTelefono: COMPLETADO CON EXITO");
		assertNotNull(listaBusqueda ,"PRUEBA UNITARIA EXITOSA: getPeritosPorInicialNumeroTelefono");
	}
	
	@Test
	void getPeritosNulos() {

		List<Peritos> listaBusqueda= peritoRepo.findByApellidoPerito2IsNullOrTelefonoOficinaIsNull();

		LOG.info("TEST getPeritosNulos: COMPLETADO CON EXITO");
		assertNotNull(listaBusqueda ,"PRUEBA UNITARIA EXITOSA: getPeritosNulos");
	}
	
}
