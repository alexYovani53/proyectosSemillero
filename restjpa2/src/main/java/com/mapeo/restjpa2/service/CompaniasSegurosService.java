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

import com.mapeo.restjpa2.dto.CompaniasSegurosDto;
import com.mapeo.restjpa2.entity.CompaniasSeguros;
import com.mapeo.restjpa2.repository.CompaniasSegurosRepository;

@RestController
@RequestMapping("/companiasseguros")
@CrossOrigin

public class CompaniasSegurosService {

	
	@Autowired
	CompaniasSegurosRepository companiasRepository;
	
	@GetMapping("/GetAll")
	public List<CompaniasSeguros> getCompaniasSeguros(){
		return companiasRepository.findAll();
	}
	
	
	@PostMapping("/Post")
	public CompaniasSeguros guardar(@RequestBody CompaniasSegurosDto companiasSegurosDto) {
		
		CompaniasSeguros nuevaCompaniaSeguros = new CompaniasSeguros();
		nuevaCompaniaSeguros.setId(companiasSegurosDto.getId());
		nuevaCompaniaSeguros.setNombreCompania(companiasSegurosDto.getNombreCompania());
		nuevaCompaniaSeguros.setNumeroPoliza(companiasSegurosDto.getNumeroPoliza());
		
		return companiasRepository.save(nuevaCompaniaSeguros);
	}

	@DeleteMapping("/Delete/{id}")
	public void eliminarCompaniasSeguros(@PathVariable("id") Integer id) {
		Optional<CompaniasSeguros> companiasSegurosBusqueda =  companiasRepository.findById(id);
		if(companiasSegurosBusqueda.isPresent()) {
			companiasRepository.delete(companiasSegurosBusqueda.get());
		}
	}

	
	@GetMapping("/DSL5")
	public List<CompaniasSeguros> getFiltrandoPorNombreCompanias(@RequestParam("nombreCompania") String nombreCompania){
		return companiasRepository.findByNombreCompaniaNot(nombreCompania);
	}
	
	
	@PostMapping("/DSL6")
	public List<CompaniasSeguros> getFiltrandoCompania(@RequestParam("numeroPoliza") Integer numeroPoliza){
		return companiasRepository.findByNumeroPolizaBefore(numeroPoliza);
	}
	
}
