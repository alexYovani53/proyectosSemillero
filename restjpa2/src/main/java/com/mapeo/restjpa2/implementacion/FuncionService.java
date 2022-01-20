package com.mapeo.restjpa2.implementacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mapeo.restjpa2.dto.SegurosDto;
import com.mapeo.restjpa2.service.Funciones;
import com.mapeo.restjpa2.ws.FuncionServiceInterface;

@Component
public class FuncionService implements  FuncionServiceInterface{

	@Autowired
	Funciones funciones;
	
	@Override
	public int guardarSeguro(SegurosDto seguroNuevo) {
		return funciones.insertarPolizaConDosSalidas(seguroNuevo);
	}

}
