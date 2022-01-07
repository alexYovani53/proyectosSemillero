package com.mapeo.restjpa2.dto;

import java.io.Serializable;
import java.util.Date;

public class SegurosDto  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5761682978859591302L;

	private Integer numeroPoliza;

	private String ramo;

	private Date fechaInicio ;

	private Date fechaVencimiento;

	private String condicionesParticulares;

	private Integer dniCl;

	public Integer getNumeroPoliza() {
		return numeroPoliza;
	}

	public void setNumeroPoliza(Integer numeroPoliza) {
		this.numeroPoliza = numeroPoliza;
	}

	public String getRamo() {
		return ramo;
	}

	public void setRamo(String ramo) {
		this.ramo = ramo;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public String getCondicionesParticulares() {
		return condicionesParticulares;
	}

	public void setCondicionesParticulares(String condicionesParticulares) {
		this.condicionesParticulares = condicionesParticulares;
	}

	public Integer getDniCl() {
		return dniCl;
	}

	public void setDniCl(Integer dniCl) {
		this.dniCl = dniCl;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
