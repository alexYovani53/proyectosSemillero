package com.mapeo.restjpa2.implementacion;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.mapeo.restjpa2.dto.CompaniasSegurosDto;
import com.mapeo.restjpa2.entity.CompaniasSeguros;
import com.mapeo.restjpa2.repository.CompaniasSegurosRepository;
import com.mapeo.restjpa2.ws.CompaniasSegurosServiceInterface;


@Component
public class CompaniasSegurosService implements CompaniasSegurosServiceInterface{

	
	@Autowired
	CompaniasSegurosRepository companiasRepository;
	
	@Override
	public List<CompaniasSeguros> getCompaniasSeguros(){
		return companiasRepository.findAll();
	}
	
	
	@Override
	public CompaniasSeguros guardar(@RequestBody CompaniasSegurosDto companiasSegurosDto) {
		
		ModelMapper map =  new ModelMapper();
		CompaniasSeguros nuevaCompaniaSeguros =  map.map(companiasSegurosDto, CompaniasSeguros.class);
		
		return companiasRepository.save(nuevaCompaniaSeguros);
	}

	@Override
	public void eliminarCompaniasSeguros(@PathVariable("id") Integer id) {
		Optional<CompaniasSeguros> companiasSegurosBusqueda =  companiasRepository.findById(id);
		if(companiasSegurosBusqueda.isPresent()) {
			companiasRepository.delete(companiasSegurosBusqueda.get());
		}
	}

	
	@Override
	public List<CompaniasSeguros> getFiltrandoPorNombreCompanias(@RequestParam("nombreCompania") String nombreCompania){
		return companiasRepository.findByNombreCompaniaNot(nombreCompania);
	}
	
	
	@Override
	public List<CompaniasSeguros> getFiltrandoCompania(@RequestParam("numeroPoliza") Integer numeroPoliza){
		return companiasRepository.findByNumeroPolizaBefore(numeroPoliza);
	}
	
}
