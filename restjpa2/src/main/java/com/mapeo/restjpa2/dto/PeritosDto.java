package com.mapeo.restjpa2.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class PeritosDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5889096102936638196L;

	private Integer dniPerito;

	private String nombrePerito;
	
	private String apellidoPerito1;
	
	private String apellidoPerito2;
	
	private Integer telefonoContacto;
	
	private Integer telefonoOficina;
	
	private String claseVia;
	
	private String nombreVia;
	
	private Integer numeroVia;
	
	private Integer codPostal;
	
	

	private String ciudad;

}
