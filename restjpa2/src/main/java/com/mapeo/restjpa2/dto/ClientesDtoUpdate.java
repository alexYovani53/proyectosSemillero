package com.mapeo.restjpa2.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class ClientesDtoUpdate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4944803766508115764L;

	
	private Integer dniCliente; 

	private  String nombreCliente;


}


