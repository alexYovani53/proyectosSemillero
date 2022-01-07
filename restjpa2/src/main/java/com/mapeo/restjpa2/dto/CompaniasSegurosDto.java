package com.mapeo.restjpa2.dto;

import java.io.Serializable;


public class CompaniasSegurosDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5393827985428148929L;

	
	private Integer id;
	
	private Integer numeroPoliza;
	
	private String nombreCompania;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumeroPoliza() {
		return numeroPoliza;
	}

	public void setNumeroPoliza(Integer numeroPoliza) {
		this.numeroPoliza = numeroPoliza;
	}

	public String getNombreCompania() {
		return nombreCompania;
	}

	public void setNombreCompania(String nombreCompania) {
		this.nombreCompania = nombreCompania;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

}
