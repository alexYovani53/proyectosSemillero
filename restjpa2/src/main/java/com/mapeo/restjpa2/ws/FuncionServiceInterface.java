package com.mapeo.restjpa2.ws;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mapeo.restjpa2.dto.SegurosDto;

@RestController
@RequestMapping("/funciones")
@CrossOrigin
public interface FuncionServiceInterface {

	
	@PostMapping("/guardar/seguro")
	public int guardarSeguro(@RequestBody SegurosDto seguroNuevo);
	
}
