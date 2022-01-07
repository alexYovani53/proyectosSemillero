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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mapeo.restjpa2.dto.SiniestrosDto;
import com.mapeo.restjpa2.entity.Siniestros;
import com.mapeo.restjpa2.repository.SiniestroRepository;

@RestController
@RequestMapping("/siniestros")
@CrossOrigin
public class SiniestroService {

	@Autowired
	SiniestroRepository siniestroRepo;
	
	@GetMapping("/GetAll")
	public List<Siniestros> getSiniestros(){
		return siniestroRepo.findAll();
	}
	
	@PostMapping("/Post")
	public Siniestros guardarSiniestro(@RequestBody SiniestrosDto siniestrosDto) {
		
		Siniestros siniestro =  convertirSiniestrosDtoASiniestros(siniestrosDto);
		
		return siniestroRepo.save(siniestro);
	}
	
	@DeleteMapping("/Delete/{id}")
	public void eliminarSiniestro(@PathVariable("id") Integer id) {
		Optional<Siniestros> siniestroBusqueda =  siniestroRepo.findById(id);
		if(siniestroBusqueda.isPresent()) {
			siniestroRepo.delete(siniestroBusqueda.get());
		}
	}
	
	@GetMapping("/DSL10")
	public List<Siniestros> getSiniestros(@RequestParam("limit1") Integer limit1, @RequestParam("limit2") Integer limit2){
		return siniestroRepo.findByIndemnizacionBetween(limit1, limit2);
	}
	
	
	public Siniestros convertirSiniestrosDtoASiniestros(SiniestrosDto siniestrosDto) {
		
		Siniestros siniestro =  new Siniestros();
		
		siniestro.setAceptado(siniestrosDto.getAceptado());
		siniestro.setCausas(siniestrosDto.getCausas());
		siniestro.setDniPerito(siniestrosDto.getDniPerito());
		siniestro.setFechaSiniestro(siniestrosDto.getFechaSiniestro());
		siniestro.setIdSiniestro(siniestrosDto.getIdSiniestro());
		siniestro.setIndemnizacion(siniestrosDto.getIndemnizacion());
		siniestro.setNumeroPoliza(siniestrosDto.getNumeroPoliza());
		
		return siniestro;
	}
	
	
	
}
