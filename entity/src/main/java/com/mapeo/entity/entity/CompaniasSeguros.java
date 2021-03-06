package com.mapeo.entity.entity;

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
@Table(name="COMPANIAS_SEGUROS")
@Data
public class CompaniasSeguros implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7178913671192534405L;

	@Id        
	@SequenceGenerator(name = "sequencia_companias_seguros", sequenceName = "sequencia_companias_seguros", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "sequencia_companias_seguros")
	@Column(name="ID")
	private Integer id;
	
	
	@Column(name="NUMERO_POLIZA")
	private Integer numeroPoliza;
	
	
	@Column(name="NOMBRE_COMPANIA")
	private String nombreCompania;

}
