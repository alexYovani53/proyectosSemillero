package com.mapeo.restjpa2.implementacion;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.mapeo.restjpa2.dto.SiniestrosDto;
import com.mapeo.entity.entity.Siniestros;
import com.mapeo.restjpa2.repository.SiniestroRepository;
import com.mapeo.restjpa2.ws.SiniestroServiceInterface;

@Component
public class SiniestroService implements SiniestroServiceInterface{

	@Autowired
	SiniestroRepository siniestroRepo;
	
	@Override
	public List<Siniestros> getSiniestros(){
		return siniestroRepo.findAll();
	}
	
	@Override
	public Siniestros guardarSiniestro(@RequestBody SiniestrosDto siniestrosDto) {
		
		Siniestros siniestro =  convertirSiniestrosDtoASiniestros(siniestrosDto);		
		return siniestroRepo.save(siniestro);
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
		
		Siniestros siniestro =  new Siniestros();		
		siniestro.setAceptado(siniestrosDto.getAceptado());
		siniestro.setCausas(siniestrosDto.getCausas());
		siniestro.setDniPerito(siniestrosDto.getDniPerito());
		siniestro.setFechaSiniestro(siniestrosDto.getFechaSiniestro());
		siniestro.setIndemnizacion(siniestrosDto.getIndemnizacion());
		siniestro.setNumeroPoliza(siniestrosDto.getNumeroPoliza());
		
		return siniestro;
	}
	
	
	
}