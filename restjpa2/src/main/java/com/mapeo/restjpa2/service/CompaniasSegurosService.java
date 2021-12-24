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

import com.mapeo.restjpa2.entity.Companias;
import com.mapeo.restjpa2.entity.CompaniasSeguros;
import com.mapeo.restjpa2.repository.CompaniasSegurosRepository;

@RestController
@RequestMapping("/companiasseguros")
@CrossOrigin

public class CompaniasSegurosService {

	
	@Autowired
	CompaniasSegurosRepository companiasRepository;
	
	@GetMapping("/prueba")
	public List<CompaniasSeguros> getCompaniasSeguros(){
		return companiasRepository.findAll();
	}
	
	
	@PostMapping("/guardar")
	public CompaniasSeguros guardar(@RequestBody CompaniasSeguros manyToMany) {
		return companiasRepository.save(manyToMany);
	}

	
}
