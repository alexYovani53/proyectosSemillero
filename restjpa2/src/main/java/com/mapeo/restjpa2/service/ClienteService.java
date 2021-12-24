package com.mapeo.restjpa2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mapeo.restjpa2.entity.Clientes;
import com.mapeo.restjpa2.repository.ClienteRepository;

@RestController
@RequestMapping("/cliente")
@CrossOrigin
public class ClienteService {

	@Autowired 
	ClienteRepository clienteRepo;
	
	@GetMapping("/prueba")
	public List<Clientes> getClientes(){
		return clienteRepo.findAll();
	}
	
	@GetMapping("/pruebaInicial/{ciudadInicial}")
	public List<Clientes> getClientesPorCiudadIniciandoPor(@PathVariable("ciudadInicial") String letra){
		return clienteRepo.findByCiudadStartingWithOrderByNombreClDesc(letra);
	}
	
	@GetMapping("/prueba/{nombre_cl}")
	public List<Clientes> getClienteByNombreCl(@PathVariable("nombre_cl") String nombre){
		return clienteRepo.findByNombreCl(nombre);
	}
	
	@DeleteMapping("/eliminar/{dni_cliente}")
	public void eliminarCliente(@PathVariable("dni_cliente") Integer dni_cliente) {
			Optional<Clientes> clienteBusqueda = clienteRepo.findById(dni_cliente);
			if(clienteBusqueda.isPresent()) {
				clienteRepo.delete(clienteBusqueda.get());
			}
	}
	
	
	
}
