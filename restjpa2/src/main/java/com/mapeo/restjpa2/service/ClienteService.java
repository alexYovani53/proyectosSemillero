package com.mapeo.restjpa2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mapeo.restjpa2.entity.Clientes;
import com.mapeo.restjpa2.repository.ClienteRepository;

@RestController
@RequestMapping("/cliente")
@CrossOrigin
public class ClienteService {

	@Autowired 
	ClienteRepository clienteRepo;
	
	@GetMapping("/prueba")
	public List<Clientes> getClientes(){
		return clienteRepo.findAll();
	}
	
}
