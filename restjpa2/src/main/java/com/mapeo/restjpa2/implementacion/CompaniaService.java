package com.mapeo.restjpa2.implementacion;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	private static final Log LOG = LogFactory.getLog(CompaniaService.class);
	
	@Autowired
	CompaniaRepository companiaRepo;
	
	@Autowired
	CatalogosService catalogoService;
	
	
	@Override
	public List<Companias> getCompanias(){
		return companiaRepo.findAll();
	}
	
	@Override
	public ResponseEntity<Companias> guardar(@RequestBody CompaniasDto companiaDto) {
		
		Companias companias = convertirCompaniasDtoACompania(companiaDto);
		try {
			return ResponseEntity.ok().body(companiaRepo.save(companias));
		} catch (Exception e) {
			LOG.error("ERROR ENCONTRADO EN, GUARDAR COMPANIAS:" + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
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
