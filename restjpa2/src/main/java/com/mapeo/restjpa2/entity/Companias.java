package com.mapeo.restjpa2.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="COMPANIAS")
public class Companias implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -670073847108820995L;

	
	@Id
	@Column(name="NOMBRE_COMPANIA")
	private String nombreCompania;
	
	@Column(name="CLASE_VIA")
	private String claseVia;
	
	@Column(name="NOMBRE_VIA")
	private String nombreVia;
	
	@Column(name="NUMERO_VIA")
	private Integer numeroVia ;
	
	@Column(name="COD_POSTAL")
	private Integer codPostal ;
	
	@Column(name="TELEFONO_CONTRATACION")
	private Integer telefonoContratacion;

	@Column(name="TELEFONO_SINIESTROS")
	private Integer telefonoSiniestros;	

	@Column(name="NOTAS")
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

	public Integer getCodPostal() {
		return codPostal;
	}

	public void setCodPostal(Integer codPostal) {
		this.codPostal = codPostal;
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
