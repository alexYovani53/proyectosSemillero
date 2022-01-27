package com.mapeo.restjpa2;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mapeo.entity.entity.Clientes;
import com.mapeo.restjpa2.dto.ClientesDto;
import com.mapeo.restjpa2.dto.ClientesDtoUpdate;
import com.mapeo.restjpa2.repository.ClienteRepository;
import com.mapeo.restjpa2.service.CatalogosService;

@SpringBootTest
class ClienteServiceTest {

	private static final Log LOG =  LogFactory.getLog(ClienteServiceTest.class);
	
	

	@Autowired 
	ClienteRepository clienteRepo;	
	
	@Autowired
	CatalogosService catalogoService;
	
	
	@Test
	void getClientes() {
		
		try {
			List<Clientes> clientesList =  clienteRepo.findAll();			
			int tamanoLista = clientesList.size();			

			LOG.info("TEST getClientes: COMPLETADO CON EXITO");
			assertNotEquals(0, tamanoLista,"PRUEBA UNITARA EXITOSA");
			
		} catch (Exception e) {
			LOG.error(e.getMessage());
			fail("Dio error: "+e.getMessage());
		}
		
	}
	
	@Test
	void eliminarCliente() {
		try {
			Optional<Clientes> clienteBusqueda = clienteRepo.findById(2);
			if(clienteBusqueda.isPresent()) {
				clienteRepo.delete(clienteBusqueda.get());
			}
			LOG.info("TEST eliminarCliente: COMPLETADO CON EXITO");
			assert(true);
			
		} catch (Exception e) {
			LOG.error("TEST eliminarCliente: NO SE COMPLETO.  ERROR: " + e.getMessage() );
			fail("Dio error: " + e.getMessage());
		}
	}
	
	
	@Test
	void guardar() {
		try {
			Clientes cliente = new Clientes();
			cliente.setApellido1("Jeronimo");
			cliente.setApellido2("Ajuchan");
			cliente.setCiudad("Guatemala");
			cliente.setClaseVia("N/A");
			cliente.setCodPostal(3004);
			cliente.setNombreCl("Alex");
			cliente.setNombreVia("N/A");
			cliente.setNumeroVia(5);
			cliente.setObservaciones("Ninguna");
			cliente.setTelefono(34383647); 
			
			clienteRepo.save(cliente);
			
			LOG.info("TEST guardar cliente nuevo: COMPLETADO CON EXITO");
			assert(true);
			
		} catch (Exception e) {

			LOG.error("TEST guardar cliente nuevo: NO SE COMPLETO.  ERROR: " + e.getMessage() );
			fail("Dio error: " + e.getMessage());
		}
	}
	
	@Test 
	void getClientesPorCiudadIniciandoPor() {
		
		List<Clientes> listaClientes =  clienteRepo.findByCiudadStartingWithOrderByNombreClDesc("G");
		

		LOG.info("TEST getClientesPorCiudadIniciandoPor: COMPLETADO CON EXITO");
		assertNotNull(listaClientes,"SE OBTUVO UNA LISTA DE CLIENTES");
		
	}
	
	@Test
	void getClienteByNombreCl() {
		
		List<Clientes> listaClientes =  clienteRepo.findByNombreCl("Alex");		

		LOG.info("TEST getClienteByNombreCl: COMPLETADO CON EXITO");
		assertNotNull(listaClientes,"SE OBTUVO UNA LISTA DE CLIENTES");
		
	}
	
	@Test
	void buscarClientes() {
		List<Map<String, Object>> listaClientes =  catalogoService.buscarCliente();

		LOG.info("TEST buscarClientes USANDO QUERY: COMPLETADO CON EXITO");
		assertNotEquals(0, listaClientes.size(),"TEST BUSCAR CLIENTES CON QUERY");
		
	}
	
	
	@Test
	void buscarCliente() {
		List<Map<String, Object>> listaClientes =  catalogoService.buscarCliente(4);

		LOG.info("TEST buscarCliente USANDO QUERY: COMPLETADO CON EXITO");
		assertNotEquals(0, listaClientes.size(),"TEST BUSCAR CLIENTES CON QUERY");
		
	}
	
	
	@Test
	void actualizarCliente() {
		
		ClientesDtoUpdate cliente =  new ClientesDtoUpdate();
		cliente.setDniCliente(4);
		cliente.setNombreCliente("Nuevo");
		
		int resultadoActualizacion = catalogoService.cambiarNombre(cliente);
		LOG.info("TEST buscarClientes USANDO QUERY: COMPLETADO CON EXITO");
		
		assertNotEquals(0,resultadoActualizacion,"TEST BUSCAR CLIENTES CON QUERY");
		
	}
	
	
	@Test
	void insertarClienteQueryNative() {
		
		ClientesDto cliente = new ClientesDto();
		cliente.setApellido1("Jeronimo");
		cliente.setApellido2("Ajuchan");
		cliente.setCiudad("Guatemala");
		cliente.setClaseVia("N/A");
		cliente.setCodPostal(3004);
		cliente.setNombreCl("Alex");
		cliente.setNombreVia("N/A");
		cliente.setNumeroVia(5);
		cliente.setObservaciones("Ninguna");
		cliente.setTelefono(34383647); 
		
		int resultadoInsert = catalogoService.insertarClienteNuevo(cliente);

		LOG.info("TEST insertarClienteQueryNative USANDO QUERY: COMPLETADO CON EXITO");
		assertNotEquals(0, resultadoInsert,"TEST BUSCAR CLIENTES CON QUERY");
		
	}
	
}
