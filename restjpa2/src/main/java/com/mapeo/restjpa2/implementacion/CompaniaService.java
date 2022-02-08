package com.mapeo.restjpa2.implementacion;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mapeo.restjpa2.dto.CompaniasDto;
import com.mapeo.restjpa2.entity.Companias;
import com.mapeo.restjpa2.repository.CompaniaRepository;
import com.mapeo.restjpa2.service.CatalogosService;
import com.mapeo.restjpa2.ws.CompaniaServiceInterface;

@RestController
@RequestMapping("/companias")
@CrossOrigin
public class CompaniaService implements CompaniaServiceInterface{

	@Autowired
	CompaniaRepository companiaRepo;
	
	@Autowired
	CatalogosService catalogoService;
	
	
	@Override
	public List<Companias> getCompanias(){
		return companiaRepo.findAll();
	}
	
	@Override
	public Companias guardar(@RequestBody CompaniasDto companiaDto) {
		
		Companias companias = convertirCompaniasDtoACompania(companiaDto);
		return companiaRepo.save(companias);
	}
	
	@Override
	public void eliminarCompania(@PathVariable("id") String id) {
		Optional<Companias> busqueda =  companiaRepo.findById(id);
		if(busqueda.isPresent()) {
			companiaRepo.delete(busqueda.get());
		}
		
	}
	
	
	@Override
	public List<Companias> getConDosParametros(@RequestParam("valor") Integer val, @RequestParam("texto") String texto){
		return companiaRepo.findByCodPostalGreaterThanAndNombreCompaniaLike(val, texto);		
	}
	
	
	@Override
	public List<Companias> getCompaniaTerminadaEn(@RequestParam("textoFinal") String textoFinal,@RequestParam("textoContenido") String textoContenido){
		return companiaRepo.findByNombreCompaniaEndingWithOrNombreCompaniaContaining(textoFinal,textoContenido);
	}
	
	public Companias convertirCompaniasDtoACompania(CompaniasDto companiaDto) {
		ModelMapper map =  new ModelMapper();
		return map.map(companiaDto, Companias.class);
	}

	@Override
	public List<Map<String, Object>> getSegurosPorCompania(String compania) {
		return catalogoService.segurosPorCompania(compania);
	}
	
	
	
	
	
}
