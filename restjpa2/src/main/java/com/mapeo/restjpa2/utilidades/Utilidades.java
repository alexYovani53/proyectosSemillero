package com.mapeo.restjpa2.utilidades;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

@Component
public class Utilidades {
	
	private static final Log LOG = LogFactory.getLog(Utilidades.class);
	
	public Date getFecha(String cadenaFecha) {
		Date fecha;
		try {
			fecha = new SimpleDateFormat("yyyy-MM-dd").parse(cadenaFecha);
		} catch (ParseException e) {
			fecha = new Date();
			LOG.error("ERROR AL PARSEAR UNA FECHA            ERROR: "+e.getMessage());
		}
		return fecha;
	}
}
