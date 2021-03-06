package com.mapeo.restjpa2.implementacion;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.mapeo.restjpa2.dto.ClientesDto;
import com.mapeo.restjpa2.dto.ClientesDtoUpdate;
import com.mapeo.restjpa2.entity.Clientes;
import com.mapeo.restjpa2.repository.ClienteRepository;
import com.mapeo.restjpa2.service.CatalogosService;
import com.mapeo.restjpa2.ws.ClientesServiceInterface;

@Component
public class ClienteService implements ClientesServiceInterface {

	@Autowired
	ClienteRepository clienteRepo;

	@Autowired
	CatalogosService catalogoService;

	@Override
	public List<Clientes> getClientes() {
		return clienteRepo.findAll();
	}

	@Override
	public void eliminarCliente(@PathVariable("dni_cliente") Integer dniCliente) {
		Optional<Clientes> clienteBusqueda = clienteRepo.findById(dniCliente);
		if (clienteBusqueda.isPresent()) {
			clienteRepo.delete(clienteBusqueda.get());
		}
	}

	@Override
	public ResponseEntity<Clientes> guardar(@RequestBody ClientesDto clienteDto) {
		
		
		try {
			Clientes cliente = convertirClientesDtoAClientes(clienteDto);
			return new ResponseEntity<>(clienteRepo.save(cliente),null,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null,null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public List<Clientes> getClientesPorCiudadIniciandoPor(@PathVariable("ciudadInicial") String letra) {
		return clienteRepo.findByCiudadStartingWithOrderByNombreClDesc(letra);
	}

	@Override
	public List<Clientes> getClienteByNombreCl(@PathVariable("nombre_cl") String nombre) {
		return clienteRepo.findByNombreCl(nombre);
	}

	private Clientes convertirClientesDtoAClientes(ClientesDto clienteDto) {

		ModelMapper map = new ModelMapper();
		return map.map(clienteDto, Clientes.class);
	}

	@Override
	public List<Map<String, Object>> buscarClientes() {
		return catalogoService.buscarCliente();
	}

	@Override
	public List<Map<String, Object>> buscarCliente(Integer dniCliente) {
		return catalogoService.buscarCliente(dniCliente);
	}

	@Override
	public ResponseEntity<Integer> actualizarCliente(ClientesDtoUpdate clienteDatos) {
		
		try {
			return new ResponseEntity<>(catalogoService.cambiarNombre(clienteDatos),null,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(0,null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<Integer> insertarClienteQueryNative(ClientesDto clienteNuevo) {

		try {
			return new ResponseEntity<>(catalogoService.insertarClienteNuevo(clienteNuevo),null,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(0,null,HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/*
	 * Servicio de Anibal
	 */

	@Override
	public List<Map<String, Object>> consultaSiniestros2021(Integer dniCl) {
		return catalogoService.consultaSiniestros2021(dniCl);
	}

	/*
	 * Servicio de paginador de Anibal
	 */

	@Override
	public Page<Clientes> buscarPaginado(int pagina, int cantidad) {
		Pageable pageable = PageRequest.of(pagina, cantidad);
		return clienteRepo.findAll(pageable);
	}

	@Override
	public Page<Clientes> buscarPorCiudad(String ciudad, int pagina, int cantidad) {
		Pageable pageable = PageRequest.of(pagina, cantidad);
		return clienteRepo.findByCiudadLike(pageable, ciudad);
	}

}
