package com.mapeo.restjpa2.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;

import com.mapeo.restjpa2.dto.ClientesDto;
import com.mapeo.restjpa2.dto.ClientesDtoUpdate;

// LOGICA DE NEGOCIO PARA CREAR QUERY'S NATIVES
@Service
public class CatalogosService {

	@Autowired
	NamedParameterJdbcTemplate baseDeDatos;

	public List<Map<String, Object>> buscarCliente() {
		String query = "select * from clientes";
		SqlParameterSource parameter = new MapSqlParameterSource();
		return baseDeDatos.queryForList(query, parameter);
	}

	public List<Map<String, Object>> buscarCliente(Integer dniCliente) {
		String query = "select * from clientes where DNI_CL =  :dni_Cliente";
		SqlParameterSource parameter = new MapSqlParameterSource().addValue("dni_Cliente", dniCliente);
		return baseDeDatos.queryForList(query, parameter);
	}

	public List<Map<String, Object>> segurosPorCompania(String compania) {
		String query = "select seguros.numero_poliza,seguros.fecha_inicio, seguros.fecha_vencimiento from seguros, companias_seguros,companias\r\n"
				+ "where seguros.numero_poliza = companias_seguros.numero_poliza and companias_seguros.nombre_compania = companias.nombre_compania \r\n"
				+ "and companias.nombre_compania = :nombreCompania";
		SqlParameterSource parameters = new MapSqlParameterSource().addValue("nombreCompania", compania);

		return baseDeDatos.queryForList(query, parameters);
	}

	public int cambiarNombre(ClientesDtoUpdate clienteDatos) {
		String query = "update clientes set NOMBRE_CL= :nombre where DNI_CL = :dniCliente";
		SqlParameterSource parameter = new MapSqlParameterSource().addValue("nombre", clienteDatos.getNombreCliente())
				.addValue("dniCliente", clienteDatos.getDniCliente());
		return baseDeDatos.update(query, parameter);
	}

	public int eliminarClientePorDni(Integer dniCliente) {
		String query = "delete from clientes whhere DNI_CL = :dniCliente";
		SqlParameterSource parameter = new MapSqlParameterSource().addValue("dniCliente", dniCliente);
		return baseDeDatos.update(query, parameter);
	}

	public int insertarClienteNuevo(ClientesDto cliente) {
		String query = "DECLARE\r\n" + "    dniCliente Number;\r\n" + "BEGIN\r\n"
				+ "    dniCliente := SEQUENCIA_CLIENTES.Nextval();\r\n"
				+ "insert into clientes(DNI_CL,NOMBRE_CL,APELLIDO_1,APELLIDO_2,CLASE_VIA,NUMERO_VIA,COD_POSTAL,CIUDAD,TELEFONO,OBSERVACIONES,NOMBRE_VIA) values(dniCliente,:NOMBRE_CL,:APELLIDO_1,:APELLIDO_2,:CLASE_VIA,:NUMERO_VIA,:COD_POSTAL,:CIUDAD,:TELEFONO,:OBSERVACIONES,:NOMBRE_VIA);\r\n"
				+ "END;\r\n" + "";
		SqlParameterSource parameter = new MapSqlParameterSource().addValue("NOMBRE_CL", cliente.getNombreCl())
				.addValue("APELLIDO_1", cliente.getApellido1()).addValue("APELLIDO_2", cliente.getApellido2())
				.addValue("CLASE_VIA", cliente.getClaseVia()).addValue("NUMERO_VIA", cliente.getNumeroVia())
				.addValue("COD_POSTAL", cliente.getCodPostal()).addValue("CIUDAD", cliente.getCiudad())
				.addValue("TELEFONO", cliente.getTelefono()).addValue("OBSERVACIONES", cliente.getObservaciones())
				.addValue("NOMBRE_VIA", cliente.getNombreVia());

		return baseDeDatos.update(query, parameter);
	}

	/*
	 * Servicio de Anibal, Consulta SQL
	 */

	public List<Map<String, Object>> consultaSiniestros2021(Integer dniCl) {
		String query = "SELECT CLI.NOMBRE_CL, CLI.APELLIDO_1, CLI.TELEFONO, SEG.NUMERO_POLIZA, SEG.FECHA_INICIO, SEG.FECHA_VENCIMIENTO, sini.fecha_siniestro, sini.causas, sini.indemnizacion,PERI.NOMBRE_PERITO, peri.apellido_perito1, peri.telefono_contacto FROM SEGUROS SEG\r\n"
				+ "    INNER JOIN CLIENTES CLI ON CLI.DNI_CL = SEG.DNI_CL\r\n"
				+ "    INNER JOIN SINIESTROS SINI ON SINI.NUMERO_POLIZA = seg.numero_poliza \r\n"
				+ "    INNER JOIN PERITOS PERI ON PERI.DNI_PERITO= SINI.DNI_PERITO\r\n"
				+ "    WHERE sini.fecha_siniestro BETWEEN TO_DATE ('01/01/2021','DD/MM/YY') AND TO_DATE('31/12/2021','DD/MM/YY') AND CLI.DNI_CL= :dniCl";
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("dniCl", dniCl);
		return baseDeDatos.queryForList(query, sqlParameterSource);
	}

}
