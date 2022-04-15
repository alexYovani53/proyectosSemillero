package com.mapeo.restjpa2.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;



@Entity
@Table(name = "SEGUROS")
@Data
public class Seguros implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -789210520875313341L;


	@Id	
	@SequenceGenerator(name = "sequencia_seguros", sequenceName = "sequencia_seguros", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "sequencia_seguros")
	@Column(name="NUMERO_POLIZA")
	private Integer numeroPoliza;

	@Column(name="RAMO")
	private String ramo;

	@Column(name="FECHA_INICIO")
	@Temporal(TemporalType.DATE)
	private Date fechaInicio ;

	@Column(name="FECHA_VENCIMIENTO")
	@Temporal(TemporalType.DATE)
	private Date fechaVencimiento;

	@Column(name="CONDICIONES_PARTICULARES")
	private String condicionesParticulares;

	@Column(name="DNI_CL")
	private Integer dniCl;
		

	@JoinColumn(name="DNI_CL",updatable = false,insertable = false)
	@ManyToOne(optional = false,fetch = FetchType.EAGER)
	private Clientes cliente;

	
	
	@ManyToMany(cascade = {
			CascadeType.ALL
	})
	@JoinTable(name="COMPANIAS_SEGUROS",
			joinColumns = {
					@JoinColumn(name = "NUMERO_POLIZA")
			},
			inverseJoinColumns = {
					@JoinColumn(name = "NOMBRE_COMPANIA")
			}		
	)	
	private List<Companias> companiasLista = new ArrayList<>();
	
	
	
}
