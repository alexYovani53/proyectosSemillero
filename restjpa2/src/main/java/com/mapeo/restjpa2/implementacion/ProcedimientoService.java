package com.mapeo.restjpa2.implementacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mapeo.restjpa2.dto.ProcedimientoInsertPolizaDto;
import com.mapeo.restjpa2.dto.SegurosDto;
import com.mapeo.restjpa2.service.ProcedimientoAlmacenado;
import com.mapeo.restjpa2.ws.ProcedimientoServiceInterface;

@Component
public class ProcedimientoService implements ProcedimientoServiceInterface{

	
	@Autowired 
	ProcedimientoAlmacenado procAlmacenado;

	@Override
	public int eliminarCliente(Integer dnicl) {
			return procAlmacenado.eliminar(dnicl);
	}

	@Override
	public ProcedimientoInsertPolizaDto insertarPoliza(SegurosDto seguro) {
		return procAlmacenado.insertarPolizaConDosSalidas(seguro);
	}
	
}
