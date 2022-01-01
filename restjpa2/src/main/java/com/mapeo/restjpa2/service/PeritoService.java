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

import com.mapeo.restjpa2.entity.Peritos;
import com.mapeo.restjpa2.repository.PeritoRepository;

@RestController
@RequestMapping("/peritos")
@CrossOrigin
public class PeritoService {

	@Autowired
	PeritoRepository peritoRepo;
	
	@GetMapping("/GetAll")
	public List<Peritos> getPeritos(){
		return peritoRepo.findAll();
	}
	
	@PostMapping("/Post")
	public Peritos guardarPerito(@RequestBody Peritos perito) {
		return peritoRepo.save(perito);
	}
	
	@DeleteMapping("/Delete/{id}")
	public void eliminarPerito(@PathVariable("id") Integer id) {
		Optional<Peritos> peritoExistente = peritoRepo.findById(id);
		if(peritoExistente.isPresent()) {
			peritoRepo.delete(peritoExistente.get());
		}
	}
	
	@GetMapping("/DSL7")
	public List<Peritos> getPeritosPorInicialNumeroTelefono(@RequestParam("inicialTelefono") Integer inicialTelefono){
		return peritoRepo.findByTelefonoContactoNotLike(inicialTelefono);
	}
	
	@GetMapping("/DSL8")
	public List<Peritos> getPeritosNulos(){
		return peritoRepo.findByApellidoPerito2IsNullOrTelefonoOficinaIsNull();
	}
	
}
