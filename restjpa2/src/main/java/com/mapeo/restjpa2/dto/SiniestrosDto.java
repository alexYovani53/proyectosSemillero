package com.mapeo.restjpa2.dto;

import java.io.Serializable;
import java.util.Date;


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

	public Integer getIdSiniestro() {
		return idSiniestro;
	}

	public void setIdSiniestro(Integer idSiniestro) {
		this.idSiniestro = idSiniestro;
	}

	public Date getFechaSiniestro() {
		return fechaSiniestro;
	}

	public void setFechaSiniestro(Date fechaSiniestro) {
		this.fechaSiniestro = fechaSiniestro;
	}

	public String getCausas() {
		return causas;
	}

	public void setCausas(String causas) {
		this.causas = causas;
	}

	public String getAceptado() {
		return aceptado;
	}

	public void setAceptado(String aceptado) {
		this.aceptado = aceptado;
	}

	public Integer getIndemnizacion() {
		return indemnizacion;
	}

	public void setIndemnizacion(Integer indemnizacion) {
		this.indemnizacion = indemnizacion;
	}

	public Integer getNumeroPoliza() {
		return numeroPoliza;
	}

	public void setNumeroPoliza(Integer numeroPoliza) {
		this.numeroPoliza = numeroPoliza;
	}

	public Integer getDniPerito() {
		return dniPerito;
	}

	public void setDniPerito(Integer dniPerito) {
		this.dniPerito = dniPerito;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
