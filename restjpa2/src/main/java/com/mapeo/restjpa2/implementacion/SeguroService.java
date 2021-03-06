package com.mapeo.restjpa2.implementacion;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.mapeo.restjpa2.dto.SegurosDto;
import com.mapeo.restjpa2.entity.Seguros;
import com.mapeo.restjpa2.models.PolizaCount;
import com.mapeo.restjpa2.repository.SeguroRepository;
import com.mapeo.restjpa2.ws.SegurosServiceInterface;


@Component
public class SeguroService implements SegurosServiceInterface{

	private static final Log LOG = LogFactory.getLog(SeguroService.class);
	
	@Autowired
	SeguroRepository seguroRepo;
	
	@Override
	public List<Seguros> getSeguros(){
		return seguroRepo.findAll();
	}
	
	
	@Override
	public ResponseEntity<Seguros> guardarSeguro(@RequestBody SegurosDto seguroDto) {
		
		Seguros seguro =  convertirSegurosDtoASeguros(seguroDto);
		

		LOG.info(seguroDto);
		LOG.info(seguro);
		
		LOG.info(seguro);
		try {
			return new ResponseEntity<>(seguroRepo.save(seguro),HttpStatus.OK);
		} catch (Exception e) {
			LOG.error("Error encontrado al GUARDAR SEGURO: "+ e.getMessage());
			return new ResponseEntity<>(null,null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
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

	
	@Override
	public List<Seguros> getSegurosPorCompania(@PathVariable("nombre") String nombre){
		return seguroRepo.buscarPorCompania(nombre);
	}


	@Override
	public List<PolizaCount> getCountSegurosPorCliente(Integer dniCl) {
		return seguroRepo.buscarSegurosPorCliente(dniCl);
	}

	@Override
	public List<Seguros> getSegurosPorDniCl(Integer dniCl) {
		return seguroRepo.buscarSegurosPorDniCl(dniCl);
	}

	
	
	public Seguros convertirSegurosDtoASeguros(SegurosDto seguroDto) {
		
		System.out.println(seguroDto);
		
		ModelMapper map =  new ModelMapper();
		return map.map(seguroDto, Seguros.class);
	}
	
	
}