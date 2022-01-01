package com.mapeo.restjpa2.repository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mapeo.restjpa2.entity.Companias;
import com.mapeo.restjpa2.entity.CompaniasSeguros;

@Repository("CompaniasSeguros")
public interface CompaniasSegurosRepository extends JpaRepository<CompaniasSeguros, Serializable> {

	public List<CompaniasSeguros> findByCompaniaNotAndSeguroFechaVencimientoBefore(Companias nombreCompania,Date fechaVencimiento);
	
	public List<CompaniasSeguros> findByCompaniaNombreCompaniaNot(String nombreCompania);
	
}
