package com.mapeo.restjpa2.ws;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mapeo.restjpa2.dto.SegurosDto;
import com.mapeo.restjpa2.entity.Seguros;
import com.mapeo.restjpa2.models.PolizaCount;

/**
 * @author Yovani
 *
 */
@RestController
@RequestMapping("/seguros")
@CrossOrigin
public interface SegurosServiceInterface {

	@GetMapping("/GetAll")
	public List<Seguros> getSeguros();
	
	@PostMapping("/Post")
	public ResponseEntity<Seguros> guardarSeguro(@RequestBody SegurosDto seguroDto);
	
	@DeleteMapping("/Delete/{id}")
	public void eliminarSeguro(@PathVariable("id") Integer id);
	
	@GetMapping("/DSL9/{fecha}")
	public List<Seguros> getSegurosDespuesFecha(@PathVariable("fecha") @DateTimeFormat(pattern="yyyy-MM-dd") Date fecha);

	
	/**
	 * @param nombre
	 * @return lista de seguros, filtrada por la compania con la que se relaciona.
	 */
	@GetMapping("/jpql/{nombre}")
	public List<Seguros> getSegurosPorCompania(@PathVariable("nombre") String nombre);	
	
	
	
	/**
	 * @param nombreCl
	 * @return Lista del conteo de polizas asociadas a un cliente, filtrado por el primer apellido de este. 
	 */
	@GetMapping("/jpql/cliente/{nombreCl}")
	public List<PolizaCount> getSegurosPorCliente(@PathVariable("nombreCl") String nombreCl);
}
