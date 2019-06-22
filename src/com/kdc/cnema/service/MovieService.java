package com.kdc.cnema.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.kdc.cnema.domain.Movie;


public interface MovieService {
	Movie findOneById(Integer id) throws DataAccessException;
	public List<Movie> findAll() throws DataAccessException;
	Movie save(Movie movie) throws DataAccessException;
	void deleteById(Integer id) throws DataAccessException;
}
