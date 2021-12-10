package com.semillero.apiFind.service;


import java.util.ArrayList;
import java.util.Arrays;
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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@RequestMapping("/rest")
public class MisServicios {

	@Autowired
	RestTemplate restTemplate;
	

	@GetMapping("/find/{nombre}")
	public List<resultObject> find(@PathVariable("nombre") String nombre) {
		
		List<resultObject> lista =  new ArrayList<>();		
		
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    HttpEntity entity = new HttpEntity("parameters", headers);
	        
		var response  = restTemplate.exchange("https://itunes.apple.com/search?term="+nombre
		    		,HttpMethod.GET,entity,String.class);
		 
		if(response.getStatusCode() == HttpStatus.OK) {
			
			ObjectMapper prueba = new ObjectMapper();
			try {
								
				JsonNode actual =  prueba.readTree(response.getBody()).get("results");
				
				actual.forEach(dato->{					

					resultObject itemLista = new resultObject();
					itemLista.setArtisName(dato.get("artistName").asText());
					itemLista.setTrackName(dato.get("trackName").asText());
					itemLista.setWrapperType(dato.get("wrapperType").asText());	
					itemLista.setService("itunes");
					itemLista.setServiceUrl("https://itunes.apple.com/search?term="+nombre);	
					lista.add(itemLista);
				});	
				
			} catch (Exception e) {
			}
		}
		
		
		response  = restTemplate.exchange("https://api.tvmaze.com/search/people?q="+nombre
	    		,HttpMethod.GET,entity,String.class);
	 
		if(response.getStatusCode() == HttpStatus.OK) {
			
			ObjectMapper prueba = new ObjectMapper();
			try {
								
				JsonNode actual =  prueba.readTree(response.getBody());
				
				actual.forEach(dato->{	
					JsonNode personaItem = dato.get("person");
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
	
	
	/*
	 * http://localhost:8000/rest/find/{artist}/musicVideo/10
	 * http://localhost:8000/rest/find/{artist}/movie/10
	 * http://localhost:8000/rest/find/{artist}/music/10
	 * http://localhost:8000/rest/find/{artist}/tvShow/10
	 * http://localhost:8000/rest/find/{artist}/all/10
	 * */

	@GetMapping("/find/{artist}/{typeItune}/{limit}")
	public PersonTvmaze getMediasArtist(@PathVariable("artist") String artist,
										@PathVariable("limit") int limit,
										@PathVariable("typeItune") String typeItune) {
		
		PersonTvmaze persona =  new PersonTvmaze();
		
		ResponseEntity<String> response  = restTemplate.getForEntity("https://api.tvmaze.com/search/people?q="+artist
	    		,String.class);
	 
		if(response.getStatusCode() == HttpStatus.OK) {
			
			ObjectMapper prueba = new ObjectMapper();
			try {
								
				JsonNode actual =  prueba.readTree(response.getBody());
				
				actual.forEach(dato->{	
					JsonNode personaItem = dato.get("person");
					
					persona.setName(personaItem.get("name").asText());
					persona.setCountry(personaItem.get("country").get("name").asText());
					persona.setBirthday(personaItem.get("birthday").asText());
	
				});	
				
			} catch (Exception e) {
			}
		}
		
		response  = restTemplate.getForEntity("https://itunes.apple.com/search?term="+artist+"&entity="+typeItune+"&limit="+limit
	    		,String.class);
	 
		if(response.getStatusCode() == HttpStatus.OK) {
			
			
			ObjectMapper prueba = new ObjectMapper();
			List<mediaItunes> coleccion =  new ArrayList<>();
			try {
								
				JsonNode actual =  prueba.readTree(response.getBody()).get("results");
				
				actual.forEach(dato->{					

					
					mediaItunes itemLista = new mediaItunes();
					

					if (dato.has("artistViewUrl")) itemLista.setArtistViewUrl(dato.get("artistViewUrl").asText());
					if (dato.has("trackName")) itemLista.setTrackName(dato.get("trackName").asText());
					if (dato.has("artistName")) itemLista.setArtistName(dato.get("artistName").asText());
					if (dato.has("kind")) itemLista.setKind(dato.get("kind").asText());
					if (dato.has("collectionName")) itemLista.setCollectionName(dato.get("collectionName").asText());
					if (dato.has("primaryGenreName")) itemLista.setPrimaryGenreName(dato.get("primaryGenreName").asText());
					
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
		
	        
		ResponseEntity<String> response  = restTemplate.getForEntity("https://itunes.apple.com/search?term="+nombre
		    		,String.class);
		 
		if(response.getStatusCode() == HttpStatus.OK) {
			
			ObjectMapper prueba = new ObjectMapper();
			try {
								
				JsonNode actual =  prueba.readTree(response.getBody()).get("results");
				
				actual.forEach(dato->{					

					resultObject itemLista = new resultObject();
					itemLista.setArtisName(dato.get("artistName").asText());
					itemLista.setTrackName(dato.get("trackName").asText());
					itemLista.setWrapperType(dato.get("wrapperType").asText());	
					itemLista.setService("itunes");
					itemLista.setServiceUrl("https://itunes.apple.com/search?term="+nombre);	
					lista.add(itemLista);
				});	
				
			} catch (Exception e) {
			}
		}
		
		
		response  = restTemplate.getForEntity("https://api.tvmaze.com/search/people?q="+nombre
	    		,String.class);
	 
		if(response.getStatusCode() == HttpStatus.OK) {
			
			ObjectMapper prueba = new ObjectMapper();
			try {
								
				JsonNode actual =  prueba.readTree(response.getBody());
				
				actual.forEach(dato->{	
					JsonNode personaItem = dato.get("person");
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
