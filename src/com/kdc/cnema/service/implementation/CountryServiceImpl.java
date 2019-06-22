package com.kdc.cnema.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.kdc.cnema.domain.Country;
import com.kdc.cnema.repositories.CountryRepository;
import com.kdc.cnema.service.CountryService;

@Service
public class CountryServiceImpl implements CountryService{

	@Autowired
	CountryRepository repository;
	
	@Override
	public Country findOneById(Integer id) throws DataAccessException {
		
		return repository.findById(id).get();
	}

	@Override
	public List<Country> findAll() throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Country save(Country country) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Integer id) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

}
