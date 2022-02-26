package com.mapeo.restjpa2.repository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mapeo.restjpa2.entity.Seguros;
import com.mapeo.restjpa2.models.PolizaCount;

@Repository("SeguroRepository")
public interface SeguroRepository extends JpaRepository<Seguros, Serializable>{

	List<Seguros> findByFechaInicioAfter(Date fecha);

	
	@Query("SELECT seg FROM Seguros seg "
			+ "INNER JOIN seg.companiasLista compania "
			+ "WHERE compania.nombreCompania = :nombre "
			+ "ORDER BY seg.cliente.nombreCl DESC")
	List<Seguros> buscarPorCompania(@Param("nombre") String nombre);
	
	
	@Query("SELECT  new com.mapeo.restjpa2.models.PolizaCount ( seg.numeroPoliza, count(seg) ) FROM Seguros seg "
			+ "INNER JOIN seg.cliente  cl "
			+ "WHERE cl.apellido1 LIKE :apellido1 "
			+ "GROUP BY seg.numeroPoliza "
			+ "ORDER BY seg.numeroPoliza")
	List<PolizaCount> buscarSegurosPorCliente (@Param("apellido1") String apellido1);
	
}
