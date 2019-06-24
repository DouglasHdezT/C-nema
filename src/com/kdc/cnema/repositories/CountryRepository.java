package com.kdc.cnema.repositories;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kdc.cnema.domain.Country;

/**
 * 
 * @author carlo
 *	Repositorio de entidad Country
 */
public interface CountryRepository extends JpaRepository<Country, Integer>{
	
	public Country findOneById(Integer id);
	
	public Country findOneByName(String name) throws DataAccessException;
	
	@Modifying
	@Query(value = "UPDATE pais SET status = :status WHERE id_pais = :id"
			, nativeQuery = true)
	public void updateState(@Param("id") Integer id, @Param("status") Boolean status) throws DataAccessException;
	
}
