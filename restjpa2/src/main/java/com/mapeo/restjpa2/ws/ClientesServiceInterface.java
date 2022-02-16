package com.mapeo.restjpa2.ws;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mapeo.restjpa2.dto.ClientesDto;
import com.mapeo.restjpa2.dto.ClientesDtoUpdate;
import com.mapeo.restjpa2.entity.Clientes;

@RestController
@RequestMapping("/cliente")
@CrossOrigin
//CAPA DE PRESENTACION

public interface ClientesServiceInterface {

	@GetMapping("/GetAll")
	public List<Clientes> getClientes();
	
	@DeleteMapping("/Delete/{dni_cliente}")
	public void eliminarCliente(@PathVariable("dni_cliente") Integer dniCliente);
	
	@PostMapping("/Post")
	public Clientes guardar(@RequestBody ClientesDto clienteDto) ;
	
	@GetMapping("/DSL1/{ciudadInicial}")
	public List<Clientes> getClientesPorCiudadIniciandoPor(@PathVariable("ciudadInicial") String letra);
	
	@GetMapping("/DSL2/{nombre_cl}")
	public List<Clientes> getClienteByNombreCl(@PathVariable("nombre_cl") String nombre);
	
	@GetMapping("/query/getAll")
	public List<Map<String, Object>> buscarClientes();
	
	@GetMapping("/query/get/{dniCliente}")
	public List<Map<String, Object>> buscarCliente(@PathVariable("dniCliente") Integer dniCliente);
	 
	@PostMapping("/query/update")
	public int actualizarCliente(@RequestBody ClientesDtoUpdate clienteDatos);
	
	@PostMapping("/query/insert")
	public int insertarClienteQueryNative(@RequestBody ClientesDto clienteNuevo);
	
	/*
	 * Servicio de Anibal	
	 */
	@GetMapping(path = "/query/select/{dniCl}")
	public List<Map<String, Object>> consultaSiniestros2021(@PathVariable Integer dniCl);
	//http://localhost:8585/cliente/query/select/4
}
