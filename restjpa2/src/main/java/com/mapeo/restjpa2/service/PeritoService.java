package com.mapeo.restjpa2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mapeo.restjpa2.entity.Peritos;
import com.mapeo.restjpa2.repository.PeritoRepository;

@RestController
@RequestMapping("/peritos")
@CrossOrigin
public class PeritoService {

	@Autowired
	PeritoRepository peritoRepo;
	
	@GetMapping("/prueba")
	public List<Peritos> getPeritos(){
		return peritoRepo.findAll();
	}
	
}
