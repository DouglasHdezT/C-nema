package com.kdc.cnema.repositories;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kdc.cnema.domain.Movie;
/**
 * 
 * @author carlo
 *Repositorio pelicula
 */
public interface MovieRepository extends JpaRepository<Movie, Integer>{

	public Movie findOneByTitle(String title) throws DataAccessException;
}
