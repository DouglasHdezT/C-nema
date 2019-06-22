package com.kdc.cnema.repositories;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.kdc.cnema.domain.Movie;
/**
 * 
 * @author carlo
 *Repositorio pelicula
 */
public interface MovieRepository extends JpaRepository<Movie, Integer>{

	
}
