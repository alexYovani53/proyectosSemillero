package com.mapeo.restjpa2.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class CompaniasSegurosDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5393827985428148929L;

	
	private Integer id;
	
	private Integer numeroPoliza;
	
	private String nombreCompania;

	
}
