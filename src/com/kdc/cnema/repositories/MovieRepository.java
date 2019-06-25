package com.kdc.cnema.repositories;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kdc.cnema.domain.Movie;
/**
 * 
 * @author carlo
 *Repositorio pelicula
 */
public interface MovieRepository extends JpaRepository<Movie, Integer>{

	public Movie findOneByTitle(String title) throws DataAccessException;
	
	@Modifying
	@Query(value = "UPDATE pelicula SET status = :status WHERE id_pelicula = :id"
			, nativeQuery = true)
	public void updateState(@Param("id") Integer id, @Param("status") Boolean status) throws DataAccessException;
	
	public List<Movie> findByStatus(Boolean status);
}
