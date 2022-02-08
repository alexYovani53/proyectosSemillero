package com.mapeo.restjpa2.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mapeo.restjpa2.entity.CompaniasSeguros;

@Repository("CompaniasSeguros")
public interface CompaniasSegurosRepository extends JpaRepository<CompaniasSeguros, Serializable> {

	public List<CompaniasSeguros> findByNumeroPolizaBefore(Integer numeroPoliza);
	
	public List<CompaniasSeguros> findByNombreCompaniaNot(String nombreCompania);
	
}
