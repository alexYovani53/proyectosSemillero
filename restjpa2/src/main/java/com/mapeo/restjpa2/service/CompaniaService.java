package com.mapeo.restjpa2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mapeo.restjpa2.entity.Companias;
import com.mapeo.restjpa2.repository.CompaniaRepository;

@RestController
@RequestMapping("/companias")
@CrossOrigin
public class CompaniaService {

	@Autowired
	CompaniaRepository companiaRepo;
	
	@GetMapping("/prueba")
	public List<Companias> getCompanias(){
		return companiaRepo.findAll();
	}
	
}
