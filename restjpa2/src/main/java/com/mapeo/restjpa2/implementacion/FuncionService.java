package com.mapeo.restjpa2.implementacion;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.mapeo.restjpa2.dto.SegurosDto;
import com.mapeo.restjpa2.service.Funciones;
import com.mapeo.restjpa2.ws.FuncionServiceInterface;

@Component
public class FuncionService implements  FuncionServiceInterface{

	private static final Log LOG  = LogFactory.getLog(FuncionService.class);
	
	@Autowired
	Funciones funciones;
	
	@Override
	public ResponseEntity<Integer> guardarSeguro(SegurosDto seguroNuevo) {
		
		try {
			return ResponseEntity.ok().body( funciones.insertarPolizaConDosSalidas(seguroNuevo));
		} catch (Exception e) {
			
			LOG.error("Error en funciones almacenada insertarPolizaConDosSalidas"+e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

}
