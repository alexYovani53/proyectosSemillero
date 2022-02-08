package com.mapeo.restjpa2.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="SINIESTROS")
@Data
public class Siniestros implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8944979449370142487L;

	
	@Id
	@SequenceGenerator(name = "sequencia_siniestros", sequenceName = "sequencia_siniestros", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "sequencia_siniestros")
	@Column(name="ID_SINIESTRO")
	private Integer idSiniestro;
	
	@Column(name="FECHA_SINIESTRO")
	private Date fechaSiniestro;
	
	@Column(name="CAUSAS")
	private String causas;
	
	@Column(name="ACEPTADO")
	private String aceptado;
	
	@Column(name="INDEMNIZACION")
	private Integer indemnizacion;
	
	@Column(name="NUMERO_POLIZA")
	private Integer numeroPoliza;
	
	@Column(name="DNI_PERITO")
	private Integer dniPerito;



	
}
