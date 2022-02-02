package com.mapeo.entity.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "PERITOS")
@Data
public class Peritos implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6566914272389628353L;

	
	@Id	
	@SequenceGenerator(name = "sequencia_peritos", sequenceName = "sequencia_peritos", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "sequencia_peritos")
	@Column(name="DNI_PERITO")
	private Integer dniPerito;
	
	@Column(name="NOMBRE_PERITO")
	private String nombrePerito;
	
	@Column(name="APELLIDO_PERITO1")
	private String apellidoPerito1;
	
	@Column(name="APELLIDO_PERITO2")
	private String apellidoPerito2;
	
	@Column(name="TELEFONO_CONTACTO")
	private Integer telefonoContacto;
	
	@Column(name="TELEFONO_OFICINA")
	private Integer telefonoOficina;
	
	@Column(name="CLASE_VIA")
	private String claseVia;
	
	@Column(name="NOMBRE_VIA")
	private String nombreVia;
	
	@Column(name="NUMERO_VIA")
	private Integer numeroVia;
	
	@Column(name="COD_POSTAL")
	private Integer codPostal;
	
	@Column(name="CIUDAD")
	private String ciudad;

	
	@OneToMany(cascade=CascadeType.ALL,orphanRemoval = true)
	@JoinColumn(name="DNI_PERITO")
	private List<Siniestros> siniestros;
	
	
		
	
}
