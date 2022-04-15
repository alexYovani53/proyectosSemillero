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
@Table(name="USUARIO")
@Data
public class Usuario implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = -560627854038362133L;

	
	@Id	
	@SequenceGenerator(name = "sequencia_usuarios", sequenceName = "sequencia_usuarios", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "sequencia_usuarios")
	@Column(name = "ID_USUARIO")
	Integer idUsuario;
	
	@Column(name = "USUARIO")
	String usuario;
	
	@Column(name = "CORREO")
	String correo;
	
	@Column(name = "PASSWORD")
	String password;
	
	
	
}
