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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mapeo.restjpa2.entity.Companias;
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
	public CompaniasSeguros guardar(@RequestBody CompaniasSeguros manyToMany) {
		return companiasRepository.save(manyToMany);
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
		return companiasRepository.findByCompaniaNombreCompaniaNot(nombreCompania);
	}
	
	@PostMapping("/DSL6")
	public List<CompaniasSeguros> getFiltrandoCompania(@RequestBody Companias compania,@RequestParam("fecha") @DateTimeFormat(pattern="yyyy-MM-dd") Date fecha){
		return companiasRepository.findByCompaniaNotAndSeguroFechaVencimientoBefore(compania,fecha);
	}
	
}
