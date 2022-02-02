package com.mapeo.restjpa2.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class SiniestrosDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3086837135091212026L;

	private Integer idSiniestro;
	
	private Date fechaSiniestro;
	
	private String causas;
	
	private String aceptado;

	private Integer indemnizacion;
	
	private Integer numeroPoliza;
	
	private Integer dniPerito;


}
