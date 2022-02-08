package com.mapeo.restjpa2.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="CLIENTES")
@Data
public class Clientes implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6021326029166130260L;

	
	@Id
	@SequenceGenerator(name = "sequencia_clientes", sequenceName = "sequencia_clientes", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "sequencia_clientes")
	@Column(name = "DNI_CL")
	private Integer dniCl;
	
	@Column(name= "NOMBRE_CL")
	private String nombreCl;
	
	@Column(name = "APELLIDO_1")
	private String apellido1;
	
	@Column(name = "APELLIDO_2")
	private String apellido2;
	
	@Column(name = "CLASE_VIA")
	private String claseVia;
	
	@Column(name = "NUMERO_VIA")
	private Integer numeroVia;
	
	@Column(name = "COD_POSTAL")
	private Integer codPostal;
	
	@Column(name = "CIUDAD")
	private String ciudad;
	
	@Column(name = "TELEFONO")
	private Integer telefono;
	
	@Column(name = "OBSERVACIONES")
	private String observaciones;
	
	@Column(name = "NOMBRE_VIA")
	private String nombreVia;

	
}
