package com.mapeo.restjpa2.ws;

import java.util.List;

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
import com.mapeo.entity.entity.CompaniasSeguros;

@RestController
@RequestMapping("/companiasseguros")
@CrossOrigin

public interface CompaniasSegurosServiceInterface {
	
	@GetMapping("/GetAll")
	public List<CompaniasSeguros> getCompaniasSeguros();
	
	@PostMapping("/Post")
	public CompaniasSeguros guardar(@RequestBody CompaniasSegurosDto companiasSegurosDto) ;
	
	@DeleteMapping("/Delete/{id}")
	public void eliminarCompaniasSeguros(@PathVariable("id") Integer id);
	
	@GetMapping("/DSL5")
	public List<CompaniasSeguros> getFiltrandoPorNombreCompanias(@RequestParam("nombreCompania") String nombreCompania);
	
	@PostMapping("/DSL6")
	public List<CompaniasSeguros> getFiltrandoCompania(@RequestParam("numeroPoliza") Integer numeroPoliza);	
	
}
