package com.mapeo.restjpa2.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mapeo.restjpa2.entity.Companias;

@Repository("CompaniaRepository")
public interface CompaniaRepository extends JpaRepository<Companias,Serializable> {

	
	public List<Companias> findByCodPostalGreaterThanAndNombreCompaniaLike(Integer valor, String texto);
	
	public List<Companias> findByNombreCompaniaEndingWithOrNombreCompaniaContaining(String textoFinal,String textoContenido);
	
}
