package com.mapeo.restjpa2.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="COMPANIAS_SEGUROS")
public class CompaniasSeguros implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7178913671192534405L;

	@Id
	@Column(name="ID")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="NUMERO_POLIZA")
	Seguros seguro;
	
	@ManyToOne
	@JoinColumn(name="NOMBRE_COMPANIA")
	Companias compania;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Seguros getSeguro() {
		return seguro;
	}

	public void setSeguro(Seguros seguro) {
		this.seguro = seguro;
	}

	public Companias getCompania() {
		return compania;
	}

	public void setCompania(Companias compania) {
		this.compania = compania;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	


}
