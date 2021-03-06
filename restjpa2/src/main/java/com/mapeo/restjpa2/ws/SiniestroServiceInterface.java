package com.mapeo.restjpa2.ws;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
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

import com.mapeo.restjpa2.dto.SiniestrosDto;
import com.mapeo.restjpa2.entity.Siniestros;

@RestController
@RequestMapping("/siniestros")
@CrossOrigin
public interface SiniestroServiceInterface {

	@GetMapping("/getAll")
	public List<Siniestros> getSiniestros();
	
	// USANDO PAGINADOR
	@GetMapping("/paginador/{pagina}/{cantidad}")
	public Page<Siniestros> buscarPaginado(@PathVariable("pagina") int pagina,@PathVariable("cantidad") int cantidad);
	
	
	@PostMapping("/post")
	public ResponseEntity<Siniestros> guardarSiniestro(@RequestBody SiniestrosDto siniestrosDto);
	
	@DeleteMapping("/delete/{id}")
	public void eliminarSiniestro(@PathVariable("id") Integer id);
	
	@GetMapping("/dSL10")
	public List<Siniestros> getSiniestros(@RequestParam("limit1") Integer limit1, @RequestParam("limit2") Integer limit2);
	
	@GetMapping("/nativequery/{fecha}/{causa}")
	public List<Siniestros> getSiniestrosFechaCausa(@PathVariable("fecha") @DateTimeFormat(pattern="yyyy-MM-dd") Date fecha,@PathVariable("causa") String Causa);
	
}
