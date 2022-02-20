package com.mapeo.restjpa2.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class SegurosDto  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5761682978859591302L;

	private Integer numeroPoliza;

	private String ramo;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="YYYY-MM-DD",timezone="GMT-6")
	private Date fechaInicio ;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="YYYY-MM-DD",timezone="GMT-6")
	private Date fechaVencimiento;

	private String condicionesParticulares;

	private Integer dniCl;
	
	
}
