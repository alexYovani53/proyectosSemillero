package com.mapeo.restjpa2.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import com.mapeo.restjpa2.repository.SeguroRepository;

@RestController
@RequestMapping("/seguros")
@CrossOrigin
public class SeguroService {

	@Autowired
	SeguroRepository seguroRepo;
	
	@GetMapping("/GetAll")
	public List<Seguros> getSeguros(){
		return seguroRepo.findAll();
	}
	
	
	@PostMapping("/Post")
	public Seguros guardarSeguro(@RequestBody SegurosDto seguroDto) {
		
		Seguros seguro =  convertirSegurosDtoASeguros(seguroDto);
		return seguroRepo.save(seguro);
	}
	
	@DeleteMapping("/Delete/{id}")
	public void eliminarSeguro(@PathVariable("id") Integer id) {
		Optional<Seguros> seguroBusqueda =  seguroRepo.findById(id);
		if(seguroBusqueda.isPresent()) {
			seguroRepo.delete(seguroBusqueda.get());
		}
	}

	@GetMapping("/DSL9/{fecha}")
	public List<Seguros> getSegurosDespuesFecha(@PathVariable("fecha") @DateTimeFormat(pattern="yyyy-MM-dd") Date fecha){
		return seguroRepo.findByFechaInicioAfter(fecha);
	}

	
	public Seguros convertirSegurosDtoASeguros(SegurosDto seguroDto) {
		
		Seguros seguro =  new Seguros();
		
		seguro.setCondicionesParticulares(seguroDto.getCondicionesParticulares());
		seguro.setDniCl(seguroDto.getDniCl());
		seguro.setFechaInicio(seguroDto.getFechaInicio());
		seguro.setFechaVencimiento(seguroDto.getFechaVencimiento());
		seguro.setNumeroPoliza(seguroDto.getNumeroPoliza());
		seguro.setRamo(seguroDto.getRamo());
		
		return seguro;
	}
	
}
