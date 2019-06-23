package com.kdc.cnema.repositories;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kdc.cnema.domain.Cinema;
/**
 * 
 * @author carlo
 *	Repository para entidad Sala
 */
public interface CinemaRepository extends JpaRepository<Cinema, Integer>{
	
	@Modifying
	@Query(value = "UPDATE sala SET status = :status WHERE id_sala = :id"
			, nativeQuery = true)
	public void updateState(@Param("id") Integer id, @Param("status") Boolean status) throws DataAccessException;

}
