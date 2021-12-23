package com.mapeo.restjpa2.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mapeo.restjpa2.entity.CompaniasSeguros;

@Repository("CompaniasSeguros")
public interface CompaniasSegurosRepository extends JpaRepository<CompaniasSeguros, Serializable> {

}
