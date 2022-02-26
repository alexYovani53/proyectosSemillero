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

import com.mapeo.restjpa2.dto.PeritosDto;
import com.mapeo.restjpa2.entity.Peritos;
import com.mapeo.restjpa2.repository.PeritoRepository;
import com.mapeo.restjpa2.ws.PeritoServiceInterface;



@Component
public class PeritoService implements PeritoServiceInterface{

	private static final  Log LOG  = LogFactory.getLog(PeritoService.class);
	
	@Autowired
	PeritoRepository peritoRepo;
	
	@Override
	public List<Peritos> getPeritos(){
		return peritoRepo.findAll();
	}
	
	@Override
	public ResponseEntity<Peritos> guardarPerito(@RequestBody PeritosDto peritoDto) {
		
		Peritos perito =  convertirPeritosDtoAPerito(peritoDto);
				
		try {
			return ResponseEntity.ok().body(peritoRepo.save(perito));
		} catch (Exception e) {
			LOG.error("ERROR ENCONTRADO EN, GUARDAR PERITO:" + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	
	@Override
	public void eliminarPerito(@PathVariable("id") Integer id) {
		Optional<Peritos> peritoExistente = peritoRepo.findById(id);
		if(peritoExistente.isPresent()) {
			peritoRepo.delete(peritoExistente.get());
		}
	}
	
	@Override
	public List<Peritos> getPeritosPorInicialNumeroTelefono(@RequestParam("inicialTelefono") Integer inicialTelefono){
		return peritoRepo.findByTelefonoContactoNotLike(inicialTelefono);
	}
	
	@Override
	public List<Peritos> getPeritosNulos(){
		return peritoRepo.findByApellidoPerito2IsNullOrTelefonoOficinaIsNull();
	}
	
	public Peritos convertirPeritosDtoAPerito(PeritosDto peritoDto) {
			
		ModelMapper map =  new ModelMapper();
		return map.map(peritoDto, Peritos.class);
	}
	
}
