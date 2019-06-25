package com.kdc.cnema.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kdc.cnema.domain.Movie;
import com.kdc.cnema.repositories.MovieRepository;
import com.kdc.cnema.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService{
	
	@Autowired
	MovieRepository mRepo;

	@Override
	public Movie findOneById(Integer id) throws DataAccessException {
		return mRepo.findById(id).get();
	}

	@Override
	public List<Movie> findAll(){
		return mRepo.findAll();
	}
	
	@Override
	public List<Movie> findAllActive(){
		return mRepo.findByStatus(true);
	}
	
	@Override
	public Movie findOneByTitle(String title) throws DataAccessException {
		return mRepo.findOneByTitle(title);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Movie save(Movie movie) throws DataAccessException {
		return mRepo.save(movie);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteById(Integer id) throws DataAccessException {
		mRepo.deleteById(id);
		
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateState(Integer id, Boolean state) throws DataAccessException {
		mRepo.updateState(id, state);
	}
}
