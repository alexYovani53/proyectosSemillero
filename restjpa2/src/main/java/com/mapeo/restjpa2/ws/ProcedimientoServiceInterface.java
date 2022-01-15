package com.mapeo.restjpa2.ws;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mapeo.restjpa2.dto.ProcedimientoInsertPolizaDto;
import com.mapeo.restjpa2.dto.SegurosDto;

@RestController
@RequestMapping("/procedimientos")
@CrossOrigin
public interface ProcedimientoServiceInterface {

	@GetMapping("/eliminarCliente/{dnicl}")
	public int eliminarCliente(@PathVariable("dnicl") Integer dnicl);
	
	@PostMapping("/ingresarSeguro")
	public ProcedimientoInsertPolizaDto insertarPoliza(@RequestBody SegurosDto seguro);
	
}
