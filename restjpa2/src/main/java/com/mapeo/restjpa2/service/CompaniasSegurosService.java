package com.mapeo.restjpa2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mapeo.restjpa2.entity.CompaniasSeguros;
import com.mapeo.restjpa2.repository.CompaniasSegurosRepository;

@RestController
@RequestMapping("/companiasseguros")
@CrossOrigin

public class CompaniasSegurosService {

	
	@Autowired
	CompaniasSegurosRepository companiasRepository;
	
	@RequestMapping("/prueba")
	public List<CompaniasSeguros> getCompaniasSeguros(){
		return companiasRepository.findAll();
	}
	
	
	
}
