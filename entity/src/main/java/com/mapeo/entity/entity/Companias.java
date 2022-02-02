package com.mapeo.entity.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name="COMPANIAS")
@Data
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
	
	
	@ManyToMany(mappedBy = "companiasLista",cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Seguros> segurosLista =  new ArrayList<>();
	

}
