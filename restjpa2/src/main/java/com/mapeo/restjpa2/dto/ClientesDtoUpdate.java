package com.mapeo.restjpa2.dto;

import java.io.Serializable;

public class ClientesDtoUpdate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4944803766508115764L;

	
	private Integer dniCliente; 

	private  String nombreCliente;


	public String getNombreCliente() {
		return nombreCliente;
	}


	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public Integer getDniCliente() {
		return dniCliente;
	}


	public void setDniCliente(Integer dniCliente) {
		this.dniCliente = dniCliente;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	} 
	
}


