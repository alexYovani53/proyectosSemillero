package com.semillero.apiFind.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.semillero.apiFind.model.PersonTvmaze;
import com.semillero.apiFind.model.mediaItunes;
import com.semillero.apiFind.model.resultObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@RequestMapping("/rest")
public class MisServicios {

	
	private static final String URL = "https://itunes.apple.com/search?term=";
	
	@Autowired
	RestTemplate restTemplate;
	

	@GetMapping("/find/{nombre}")
	public List<resultObject> find(@PathVariable("nombre") String nombre) {
		
		List<resultObject> lista =  new ArrayList<>();		
		
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    HttpEntity entity = new HttpEntity("parameters", headers);
	        
		var response  = restTemplate.exchange(URL+nombre
		    		,HttpMethod.GET,entity,String.class);
		 
		if(response.getStatusCode() == HttpStatus.OK) {
			
			ObjectMapper objectJson = new ObjectMapper();
			try {
								
				JsonNode nodoRaiz =  objectJson.readTree(response.getBody()).get("results");
				
				nodoRaiz.forEach(resultado->{					

					resultObject itemLista = new resultObject();
					itemLista.setArtisName(resultado.get("artistName").asText());
					itemLista.setTrackName(resultado.get("trackName").asText());
					itemLista.setWrapperType(resultado.get("wrapperType").asText());	
					itemLista.setService("itunes");
					itemLista.setServiceUrl(URL+nombre);	
					lista.add(itemLista);
				});	
				
			} catch (Exception e) {
			}
		}
		
		
		response  = restTemplate.exchange("https://api.tvmaze.com/search/people?q="+nombre
	    		,HttpMethod.GET,entity,String.class);
	 
		if(response.getStatusCode() == HttpStatus.OK) {
			
			ObjectMapper objectJson = new ObjectMapper();
			try {
								
				JsonNode nodoRaiz =  objectJson.readTree(response.getBody());
				
				nodoRaiz.forEach(persona->{	
					JsonNode personaItem = persona.get("person");
					resultObject itemLista = new resultObject();
					itemLista.setArtisName(personaItem.get("name").asText());
					itemLista.setService("tvmaze");
					itemLista.setServiceUrl("https://api.tvmaze.com/search/people?q="+nombre);
					
					lista.add(itemLista);					
				});	
				
			} catch (Exception e) {
			}
		}
	
		return lista;
	}
	
	
	/**
	 * http://localhost:8000/rest/find/{artist}/musicVideo/10
	 * http://localhost:8000/rest/find/{artist}/movie/10
	 * http://localhost:8000/rest/find/{artist}/music/10
	 * http://localhost:8000/rest/find/{artist}/tvShow/10
	 * http://localhost:8000/rest/find/{artist}/all/10
	 * 
	 */
 
	@GetMapping("/find/{artist}/{typeItune}/{limit}")
	public PersonTvmaze getMediasArtist(@PathVariable("artist") String artist,
										@PathVariable("limit") int limit,
										@PathVariable("typeItune") String typeItune) {
		
		PersonTvmaze persona =  new PersonTvmaze();
		
		ResponseEntity<String> response  = restTemplate.getForEntity("https://api.tvmaze.com/search/people?q="+artist
	    		,String.class);
	 
		if(response.getStatusCode() == HttpStatus.OK) {
			
			ObjectMapper objectJson = new ObjectMapper();
			try {
								
				JsonNode nodoRaiz =  objectJson.readTree(response.getBody());
				
				nodoRaiz.forEach(subNodo->{	
					
					JsonNode personaItem = subNodo.get("person");
					
					persona.setName(personaItem.get("name").asText());
					persona.setCountry(personaItem.get("country").get("name").asText());
					persona.setBirthday(personaItem.get("birthday").asText());
	
				});	
				
			} catch (Exception e) {
				
			}
		}
		
		response  = restTemplate.getForEntity(URL+artist+"&entity="+typeItune+"&limit="+limit
	    		,String.class);
	 
		if(response.getStatusCode() == HttpStatus.OK) {
			
			
			ObjectMapper jsonObject = new ObjectMapper();
			List<mediaItunes> coleccion =  new ArrayList<>();
			try {
								
				JsonNode resultadosItunes =  jsonObject.readTree(response.getBody()).get("results");
				
				resultadosItunes.forEach(itemResultado->{					

					
					mediaItunes itemLista = new mediaItunes();
					

					if (itemResultado.has("artistViewUrl")) itemLista.setArtistViewUrl(itemResultado.get("artistViewUrl").asText());
					if (itemResultado.has("trackName")) itemLista.setTrackName(itemResultado.get("trackName").asText());
					if (itemResultado.has("artistName")) itemLista.setArtistName(itemResultado.get("artistName").asText());
					if (itemResultado.has("kind")) itemLista.setKind(itemResultado.get("kind").asText());
					if (itemResultado.has("collectionName")) itemLista.setCollectionName(itemResultado.get("collectionName").asText());
					if (itemResultado.has("primaryGenreName")) itemLista.setPrimaryGenreName(itemResultado.get("primaryGenreName").asText());
					
					coleccion.add(itemLista);
				});	
				
				persona.setMedias(coleccion);
				
			} catch (Exception e) {

			}
		}
		
		
		return persona;
	}


	
	@GetMapping("/find2/{nombre}")
	public List<resultObject> find2(@PathVariable("nombre") String nombre) {
		

		List<resultObject> lista =  new ArrayList<>();			        
		ResponseEntity<String> response  = restTemplate.getForEntity(URL+nombre
		    		,String.class);
		
		if(response.getStatusCode() == HttpStatus.OK) {
			
			ObjectMapper jsonObject = new ObjectMapper();
			try {
								
				JsonNode nodoRaiz =  jsonObject.readTree(response.getBody()).get("results");
				
				nodoRaiz.forEach(itemResult->{					

					resultObject itemLista = new resultObject();
					itemLista.setArtisName(itemResult.get("artistName").asText());
					itemLista.setTrackName(itemResult.get("trackName").asText());
					itemLista.setWrapperType(itemResult.get("wrapperType").asText());	
					itemLista.setService("itunes");
					itemLista.setServiceUrl(URL+nombre);	
					lista.add(itemLista);
				});	
				
			} catch (Exception e) {
			}
		}
		
		
		response  = restTemplate.getForEntity("https://api.tvmaze.com/search/people?q="+nombre
	    		,String.class);
	 
		if(response.getStatusCode() == HttpStatus.OK) {
			
			ObjectMapper jsonObject = new ObjectMapper();
			try {
								
				JsonNode nodoRaiz =  jsonObject.readTree(response.getBody());
				
				nodoRaiz.forEach(persona->{	
					JsonNode personaItem = persona.get("person");
					resultObject itemLista = new resultObject();
					itemLista.setArtisName(personaItem.get("name").asText());
					itemLista.setService("tvmaze");
					itemLista.setServiceUrl("https://api.tvmaze.com/search/people?q="+nombre);
					
					lista.add(itemLista);					
				});	
				
			} catch (Exception e) {
			}
		}
	
		return lista;
	}
	
}
