package com.mapeo.restjpa2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mapeo.restjpa2.dto.ClientesDto;
import com.mapeo.restjpa2.entity.Clientes;
import com.mapeo.restjpa2.repository.ClienteRepository;

@RestController
@RequestMapping("/cliente")
@CrossOrigin
public class ClienteService {

	@Autowired 
	ClienteRepository clienteRepo;
	
	@GetMapping("/GetAll")
	public List<Clientes> getClientes(){
		return clienteRepo.findAll();
	}
	
	@DeleteMapping("/Delete/{dni_cliente}")
	public void eliminarCliente(@PathVariable("dni_cliente") Integer dniCliente) {
			Optional<Clientes> clienteBusqueda = clienteRepo.findById(dniCliente);
			if(clienteBusqueda.isPresent()) {
				clienteRepo.delete(clienteBusqueda.get());
			}
	}
	
	@PostMapping("/Post")
	public Clientes guardar(@RequestBody ClientesDto clienteDto) {
		
		Clientes cliente =  convertirClientesDtoAClientes(clienteDto);
		
		return clienteRepo.save(cliente);
	}
	
	
	@GetMapping("/DSL1/{ciudadInicial}")
	public List<Clientes> getClientesPorCiudadIniciandoPor(@PathVariable("ciudadInicial") String letra){
		return clienteRepo.findByCiudadStartingWithOrderByNombreClDesc(letra);
	}
	
	@GetMapping("/DSL2/{nombre_cl}")
	public List<Clientes> getClienteByNombreCl(@PathVariable("nombre_cl") String nombre){
		return clienteRepo.findByNombreCl(nombre);
	}
	
	private Clientes convertirClientesDtoAClientes(ClientesDto clienteDto) {
		Clientes cliente = new Clientes();
		cliente.setApellido1(clienteDto.getApellido1());
		cliente.setApellido2(clienteDto.getApellido2());
		cliente.setCiudad(clienteDto.getCiudad());
		cliente.setClaseVia(clienteDto.getClaseVia());
		cliente.setCodPostal(clienteDto.getCodPostal());
		cliente.setDniCl(clienteDto.getDniCl());
		cliente.setNombreCl(clienteDto.getNombreCl());
		cliente.setNombreVia(clienteDto.getNombreVia());
		cliente.setNumeroVia(clienteDto.getNumeroVia());
		cliente.setObservaciones(clienteDto.getObservaciones());
		cliente.setTelefono(clienteDto.getTelefono());
		return cliente;
	}
	

}
