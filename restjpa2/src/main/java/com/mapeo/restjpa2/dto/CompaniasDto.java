package com.mapeo.restjpa2.dto;

import java.io.Serializable;

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

	public String getNombreCompania() {
		return nombreCompania;
	}

	public void setNombreCompania(String nombreCompania) {
		this.nombreCompania = nombreCompania;
	}

	public String getClaseVia() {
		return claseVia;
	}

	public void setClaseVia(String claseVia) {
		this.claseVia = claseVia;
	}

	public String getNombreVia() {
		return nombreVia;
	}

	public void setNombreVia(String nombreVia) {
		this.nombreVia = nombreVia;
	}

	public Integer getNumeroVia() {
		return numeroVia;
	}

	public void setNumeroVia(Integer numeroVia) {
		this.numeroVia = numeroVia;
	}

	public Integer getCodPostal() {
		return codPostal;
	}

	public void setCodPostal(Integer codPostal) {
		this.codPostal = codPostal;
	}

	public Integer getTelefonoContratacion() {
		return telefonoContratacion;
	}

	public void setTelefonoContratacion(Integer telefonoContratacion) {
		this.telefonoContratacion = telefonoContratacion;
	}

	public Integer getTelefonoSiniestros() {
		return telefonoSiniestros;
	}

	public void setTelefonoSiniestros(Integer telefonoSiniestros) {
		this.telefonoSiniestros = telefonoSiniestros;
	}

	public String getNotas() {
		return notas;
	}

	public void setNotas(String notas) {
		this.notas = notas;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
