package com.mapeo.restjpa2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mapeo.restjpa2.entity.Seguros;
import com.mapeo.restjpa2.repository.SeguroRepository;

@RestController
@RequestMapping("/seguros")
@CrossOrigin
public class SeguroService {

	@Autowired
	SeguroRepository seguroRepo;
	
	@GetMapping("/prueba")
	public List<Seguros> getSeguros(){
		return seguroRepo.findAll();
	}
	
}
