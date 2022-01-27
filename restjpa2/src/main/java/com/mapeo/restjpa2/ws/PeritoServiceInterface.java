package com.mapeo.restjpa2.ws;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mapeo.restjpa2.dto.PeritosDto;
import com.mapeo.entity.entity.Peritos;

@RestController
@RequestMapping("/peritos")
@CrossOrigin
public interface PeritoServiceInterface {

	@GetMapping("/GetAll")
	public List<Peritos> getPeritos();
	
	@PostMapping("/Post")
	public Peritos guardarPerito(@RequestBody PeritosDto peritoDto) ;
	
	@DeleteMapping("/Delete/{id}")
	public void eliminarPerito(@PathVariable("id") Integer id);
	
	@GetMapping("/DSL7")
	public List<Peritos> getPeritosPorInicialNumeroTelefono(@RequestParam("inicialTelefono") Integer inicialTelefono);
	
	@GetMapping("/DSL8")
	public List<Peritos> getPeritosNulos();
	
}