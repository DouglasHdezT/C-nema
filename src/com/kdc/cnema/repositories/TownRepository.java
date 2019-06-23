package com.kdc.cnema.repositories;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kdc.cnema.domain.Town;

public interface TownRepository extends JpaRepository<Town, Integer>{

	public Town findOneByName(String name) throws DataAccessException;
	
	@Modifying
	@Query(value = "UPDATE municipio SET status = :status WHERE id_municipio = :id"
			, nativeQuery = true)
	public void updateState(@Param("id") Integer id, @Param("status") Boolean status) throws DataAccessException;
}
