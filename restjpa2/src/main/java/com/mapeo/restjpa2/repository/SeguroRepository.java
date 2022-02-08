package com.mapeo.restjpa2.repository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mapeo.restjpa2.entity.Seguros;

@Repository("SeguroRepository")
public interface SeguroRepository extends JpaRepository<Seguros, Serializable>{

	List<Seguros> findByFechaInicioAfter(Date fecha);

}
