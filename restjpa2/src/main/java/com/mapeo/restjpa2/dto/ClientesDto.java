package com.mapeo.restjpa2.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class ClientesDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8335264445762359160L;

	private Integer dniCl;
	
	private String nombreCl;
	
	private String apellido1;
	
	private String apellido2;
	
	private String claseVia;
	
	private Integer numeroVia;
	
	private Integer codPostal;
	
	private String ciudad;
	
	private Integer telefono;
	
	private String observaciones;
	
	private String nombreVia;


	
	
}
