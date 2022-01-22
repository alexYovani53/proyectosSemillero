package com.mapeo.restjpa2.service;


import java.sql.Types;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import com.mapeo.restjpa2.dto.SegurosDto;

@Service
public class Funciones {

	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
	public int insertarPolizaConDosSalidas (SegurosDto dtoRequest) {
		
		try {
			SimpleJdbcCall simpleJdbcCall =  new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.withFunctionName("func_ingreso_poliza_retorno").withCatalogName("practica2")
				.declareParameters(						
						new SqlParameter("ramo",Types.VARCHAR),
						new SqlParameter("fechain",Types.DATE),
						new SqlParameter("fechafin",Types.DATE),
						new SqlParameter("condiciones",Types.VARCHAR),
						new SqlInOutParameter("dnicl", Types.NUMERIC)
				);
			
			SqlParameterSource parametros =  new MapSqlParameterSource()
					.addValue("ramo", dtoRequest.getRamo())
					.addValue("fechain",dtoRequest.getFechaInicio())
					.addValue("fechafin",dtoRequest.getFechaVencimiento())
					.addValue("condiciones", dtoRequest.getCondicionesParticulares())
					.addValue("dnicl", dtoRequest.getDniCl());
			
			Object out =  simpleJdbcCall.executeFunction(Integer.class,parametros);
				
			return Integer.parseInt(out.toString());			
			
		} catch (Exception e) {

			return -1;
		}
		
	}
	
	
	
}
