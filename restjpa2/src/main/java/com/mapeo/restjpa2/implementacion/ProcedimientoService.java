package com.mapeo.restjpa2.implementacion;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.mapeo.restjpa2.dto.ProcedimientoInsertPolizaDto;
import com.mapeo.restjpa2.dto.SegurosDto;
import com.mapeo.restjpa2.service.ProcedimientoAlmacenado;
import com.mapeo.restjpa2.ws.ProcedimientoServiceInterface;

@Component
public class ProcedimientoService implements ProcedimientoServiceInterface{

	private final static Log LOG  = LogFactory.getLog(ProcedimientoService.class);
	
	@Autowired 
	ProcedimientoAlmacenado procAlmacenado;

	@Override
	public int eliminarCliente(Integer dnicl) {
			return procAlmacenado.eliminar(dnicl);
	}

	@Override
	public ResponseEntity<ProcedimientoInsertPolizaDto> insertarPoliza(SegurosDto seguro) {
		
		try {
			return ResponseEntity.ok().body(procAlmacenado.insertarPolizaConDosSalidas(seguro));
		} catch (Exception e) {
			LOG.error("Error encontrado en procedimiento almacenado insertarPolizaConDosSalidas(...) : "+e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		
	}
	
}
