package com.mapeo.restjpa2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mapeo.restjpa2.entity.Siniestros;
import com.mapeo.restjpa2.repository.SiniestroRepository;

@RestController
@RequestMapping("/siniestros")
@CrossOrigin
public class SiniestroService {

	@Autowired
	SiniestroRepository siniestroRepo;
	
	@GetMapping("/prueba")
	public List<Siniestros> getSiniestros(){
		return siniestroRepo.findAll();
	}
	
	@GetMapping("/prueba/{limit1}/{limit2}")
	public List<Siniestros> getSiniestros(@PathVariable("limit1") Integer limit1, @PathVariable("limit2") Integer limit2){
		return siniestroRepo.findByIndemnizacionBetween(limit1, limit2);
	}
	
	
	
	
	
}
