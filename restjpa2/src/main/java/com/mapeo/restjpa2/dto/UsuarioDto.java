package com.mapeo.restjpa2.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class UsuarioDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5466504128343068578L;

	private Integer idUsuario;
	
	private String correo;
	
	private String usuario;
	
	private String password;
	
}
