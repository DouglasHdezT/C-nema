package com.kdc.cnema.repositories;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kdc.cnema.domain.Depto;
/**
 * 
 * @author carlo
 *Repositorio Departamento
 */
public interface DeptoRepository extends JpaRepository<Depto, Integer>{

	public Depto findOneByName(String name) throws DataAccessException;
}
