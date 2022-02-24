package com.mapeo.restjpa2.implementacion;

import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.mapeo.restjpa2.dto.SiniestrosDto;
import com.mapeo.restjpa2.entity.Siniestros;
import com.mapeo.restjpa2.repository.SiniestroRepository;
import com.mapeo.restjpa2.ws.SiniestroServiceInterface;

@Component
public class SiniestroService implements SiniestroServiceInterface{

	
	private static final Log LOG  = LogFactory.getLog(SiniestroService.class);
	
	@Autowired
	SiniestroRepository siniestroRepo;
	
	@Override
	public List<Siniestros> getSiniestros(){
		return siniestroRepo.findAll();
	}
	
	@Override
	public ResponseEntity<Siniestros> guardarSiniestro(@RequestBody SiniestrosDto siniestrosDto) {
		
		Siniestros siniestro =  convertirSiniestrosDtoASiniestros(siniestrosDto);		
		
		try {
			return ResponseEntity.ok(siniestroRepo.save(siniestro));
		} catch (Exception e) {
			LOG.error("Error encontrado en GUARDAR SINIESTRO "+ e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	
	@Override
	public void eliminarSiniestro(@PathVariable("id") Integer id) {
		Optional<Siniestros> siniestroBusqueda =  siniestroRepo.findById(id);
		if(siniestroBusqueda.isPresent()) {
			siniestroRepo.delete(siniestroBusqueda.get());
		}
	}
	
	@Override
	public List<Siniestros> getSiniestros(@RequestParam("limit1") Integer limit1, @RequestParam("limit2") Integer limit2){
		return siniestroRepo.findByIndemnizacionBetween(limit1, limit2);
	}
	
	
	public Siniestros convertirSiniestrosDtoASiniestros(SiniestrosDto siniestrosDto) {
				
		ModelMapper map =  new ModelMapper();
		return map.map(siniestrosDto, Siniestros.class);
	}
	
	
	
}
