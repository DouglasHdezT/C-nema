package com.kdc.cnema.repositories;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kdc.cnema.domain.Depto;
/**
 * 
 * @author carlo
 *Repositorio Departamento
 */
public interface DeptoRepository extends JpaRepository<Depto, Integer>{

	public Depto findOneByName(String name) throws DataAccessException;
	
	@Modifying
	@Query(value = "UPDATE departamento SET state = :state WHERE id_depto = :id"
			, nativeQuery = true)
	public void updateState(@Param("id") Integer id, @Param("state") Boolean state) throws DataAccessException;
}
