package com.mapeo.restjpa2.implementacion;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.mapeo.restjpa2.dto.SegurosDto;
import com.mapeo.entity.entity.Seguros;
import com.mapeo.restjpa2.repository.SeguroRepository;
import com.mapeo.restjpa2.ws.SegurosServiceInterface;


@Component
public class SeguroService implements SegurosServiceInterface{

	@Autowired
	SeguroRepository seguroRepo;
	
	@Override
	public List<Seguros> getSeguros(){
		return seguroRepo.findAll();
	}
	
	
	@Override
	public Seguros guardarSeguro(@RequestBody SegurosDto seguroDto) {
		
		Seguros seguro =  convertirSegurosDtoASeguros(seguroDto);
		return seguroRepo.save(seguro);
	}
	
	@Override
	public void eliminarSeguro(@PathVariable("id") Integer id) {
		Optional<Seguros> seguroBusqueda =  seguroRepo.findById(id);
		if(seguroBusqueda.isPresent()) {
			seguroRepo.delete(seguroBusqueda.get());
		}
	}

	@Override
	public List<Seguros> getSegurosDespuesFecha(@PathVariable("fecha") @DateTimeFormat(pattern="yyyy-MM-dd") Date fecha){
		return seguroRepo.findByFechaInicioAfter(fecha);
	}

	
	public Seguros convertirSegurosDtoASeguros(SegurosDto seguroDto) {
		
		Seguros seguro =  new Seguros();
		
		seguro.setCondicionesParticulares(seguroDto.getCondicionesParticulares());
		seguro.setDniCl(seguroDto.getDniCl());
		seguro.setFechaInicio(seguroDto.getFechaInicio());
		seguro.setFechaVencimiento(seguroDto.getFechaVencimiento());
		seguro.setNumeroPoliza(seguroDto.getNumeroPoliza());
		seguro.setRamo(seguroDto.getRamo());
		
		return seguro;
	}
	
}
