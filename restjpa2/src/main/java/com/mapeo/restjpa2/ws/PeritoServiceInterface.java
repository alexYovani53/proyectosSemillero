package com.mapeo.restjpa2.ws;

import java.util.List;

import org.springframework.data.domain.Page;
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

import com.mapeo.restjpa2.dto.PeritosDto;
import com.mapeo.restjpa2.entity.Peritos;

@RestController
@RequestMapping("/peritos")
@CrossOrigin
public interface PeritoServiceInterface {

	@GetMapping("/GetAll")
	public List<Peritos> getPeritos();
	
	// USANDO PAGINADOR
	@GetMapping("/paginador/{pagina}/{cantidad}")
	public Page<Peritos> buscarPaginado(@PathVariable("pagina") int pagina,@PathVariable("cantidad") int cantidad);
	
	
	@PostMapping("/Post")
	public ResponseEntity<Peritos> guardarPerito(@RequestBody PeritosDto peritoDto) ;
	
	@DeleteMapping("/Delete/{id}")
	public void eliminarPerito(@PathVariable("id") Integer id);
	
	@GetMapping("/DSL7")
	public List<Peritos> getPeritosPorInicialNumeroTelefono(@RequestParam("inicialTelefono") Integer inicialTelefono);
	
	@GetMapping("/DSL8")
	public List<Peritos> getPeritosNulos();
	
	/**
	 * @param causas
	 * @return lista de peritos, filtrando por los siniestros atendidos y causas coicidentes
	 */
	@GetMapping("/jpql/{causas}")
	public List<Peritos> getPeritosSiniestrosCausas(@PathVariable("causas") String causas);
	
	
	
}
