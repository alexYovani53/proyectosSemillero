package com.mapeo.restjpa2.repository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mapeo.restjpa2.entity.Siniestros;

@Repository("SiniestroRepository")
public interface SiniestroRepository extends JpaRepository<Siniestros, Serializable>{

		
	List<Siniestros> findByIndemnizacionBetween (Integer limitInferior, Integer limiteSuperior);
	
	
	@Query(value="SELECT * FROM Siniestros WHERE FECHA_SINIESTRO > ?1 AND CAUSAS = ?2 ",nativeQuery = true)
	List<Siniestros> buscarPorFechaYCausa(Date fecha,String causa);
	
}
