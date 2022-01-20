package com.mapeo.restjpa2.implementacion;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
	public List<Clientes> getClientes(){
		return clienteRepo.findAll();
	}
	
	@Override
	public void eliminarCliente(@PathVariable("dni_cliente") Integer dniCliente) {
			Optional<Clientes> clienteBusqueda = clienteRepo.findById(dniCliente);
			if(clienteBusqueda.isPresent()) {
				clienteRepo.delete(clienteBusqueda.get());
			}
	}
	
	@Override
	public Clientes guardar(@RequestBody ClientesDto clienteDto) {		
		Clientes cliente =  convertirClientesDtoAClientes(clienteDto);		
		return clienteRepo.save(cliente);
	}	
	
	@Override
	public List<Clientes> getClientesPorCiudadIniciandoPor(@PathVariable("ciudadInicial") String letra){
		return clienteRepo.findByCiudadStartingWithOrderByNombreClDesc(letra);
	}
	
	@Override
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
		//cliente.setDniCl(clienteDto.getDniCl());
		cliente.setNombreCl(clienteDto.getNombreCl());
		cliente.setNombreVia(clienteDto.getNombreVia());
		cliente.setNumeroVia(clienteDto.getNumeroVia());
		cliente.setObservaciones(clienteDto.getObservaciones());
		cliente.setTelefono(clienteDto.getTelefono());
		return cliente;
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
	public int actualizarCliente(ClientesDtoUpdate clienteDatos) {
		return catalogoService.cambiarNombre(clienteDatos);
	}

	@Override
	public int insertarClienteQueryNative(ClientesDto clienteNuevo) {
		return catalogoService.insertarClienteNuevo(clienteNuevo);
	}
	

}
