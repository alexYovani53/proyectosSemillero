package com.mapeo.restjpa2.service;

import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import com.mapeo.restjpa2.dto.ProcedimientoInsertPolizaDto;
import com.mapeo.restjpa2.dto.SegurosDto;

@Service
public class ProcedimientoAlmacenado {

	public static final String DNI_CL = "dniCl";
	
	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public int eliminar(Integer idCliente) {
		String query = "begin\r\n"
				+ "eliminarCliente(:dniCl);\r\n"
				+ "end;";
		
		SqlParameterSource sqlParameterSource =  new MapSqlParameterSource()
				.addValue(DNI_CL,idCliente);
		
		return namedParameterJdbcTemplate.update(query, sqlParameterSource);
		
	}
	
	public ProcedimientoInsertPolizaDto insertarPolizaConDosSalidas (SegurosDto dtoRequest) {
		
		try {
			SimpleJdbcCall simpleJdbcCall =  new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.withProcedureName("ingreso_poliza_retorno")
			.withoutProcedureColumnMetaDataAccess().
				declareParameters(						
						new SqlOutParameter("numeropoliza", Types.NUMERIC),
						new SqlParameter("ramo",Types.VARCHAR),
						new SqlParameter("fechain",Types.DATE),
						new SqlParameter("fechafin",Types.DATE),
						new SqlParameter("condiciones",Types.VARCHAR),
						new SqlInOutParameter(DNI_CL, Types.NUMERIC)
				);
			
			SqlParameterSource parametros =  new MapSqlParameterSource()
					.addValue("ramo", dtoRequest.getRamo())
					.addValue("fechain",new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
					.addValue("fechafin",dtoRequest.getFechaVencimiento())
					.addValue("condiciones", dtoRequest.getCondicionesParticulares())
					.addValue(DNI_CL, dtoRequest.getDniCl());
			
			Map<String, Object> out =  simpleJdbcCall.execute(parametros);
			
			ProcedimientoInsertPolizaDto dto = new ProcedimientoInsertPolizaDto();
			dto.setPolizanumber(Integer.parseInt(out.get("numeropoliza").toString()));
			dto.setDnicl(Integer.parseInt(out.get(DNI_CL).toString()));
			
			//dto.setFecha((Date) out.get("x"));
			
			
			return dto;
			
			
		} catch (Exception e) {
			return new ProcedimientoInsertPolizaDto();
		}
		

		
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
