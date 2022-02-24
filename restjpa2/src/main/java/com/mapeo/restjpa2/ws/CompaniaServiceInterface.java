package com.mapeo.restjpa2.ws;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mapeo.restjpa2.dto.CompaniasDto;
import com.mapeo.restjpa2.entity.Companias;

@RestController
@RequestMapping("/companias")
@CrossOrigin
public interface CompaniaServiceInterface {

	@GetMapping("/GetAll")
	public List<Companias> getCompanias();
	
	@PostMapping("/Post")
	public ResponseEntity<Companias> guardar(@RequestBody CompaniasDto companiaDto) ;
	
	@DeleteMapping("/Delete/{id}")
	public void eliminarCompania(@PathVariable("id") String id) ;
	
	@GetMapping("/DSL3")
	public List<Companias> getConDosParametros(@RequestParam("valor") Integer val, @RequestParam("texto") String texto);
	
	@GetMapping("/DSL4")
	public List<Companias> getCompaniaTerminadaEn(@RequestParam("textoFinal") String textoFinal,@RequestParam("textoContenido") String textoContenido);
	
	@GetMapping("/query/segurosPorCompania")
	public List<Map<String, Object>> getSegurosPorCompania(@RequestParam("compania") String compania);
}
