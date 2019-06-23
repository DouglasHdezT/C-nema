package com.kdc.cnema.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.kdc.cnema.domain.Movie;
import com.kdc.cnema.repositories.MovieRepository;
import com.kdc.cnema.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService{
	
	@Autowired
	MovieRepository mRepo;

	@Override
	public Movie findOneById(Integer id) throws DataAccessException {
		// TODO Auto-generated method stub
		return mRepo.findById(id).get();
	}

	@Override
	public List<Movie> findAll(){
		return mRepo.findAll();
	}

	@Override
	public Movie save(Movie movie) throws DataAccessException {
		// TODO Auto-generated method stub
		return mRepo.save(movie);
	}

	@Override
	public void deleteById(Integer id) throws DataAccessException {
		// TODO Auto-generated method stub
		mRepo.deleteById(id);
		
	}

	@Override
	public Movie findOneByTitle(String title) throws DataAccessException {
		// TODO Auto-generated method stub
		return mRepo.findOneByTitle(title);
	}

}
