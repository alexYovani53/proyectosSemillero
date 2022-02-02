package com.mapeo.restjpa2.dto;

import java.io.Serializable;

import lombok.Data;


@Data
public class CompaniasDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7087488458100192073L;

	private String nombreCompania;

	private String claseVia;
	
	private String nombreVia;
	
	private Integer numeroVia ;
	
	private Integer codPostal ;
	
	private Integer telefonoContratacion;

	private Integer telefonoSiniestros;	

	private String notas;

	
	
}
