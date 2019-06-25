package com.kdc.cnema.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		return repository.findAll();
	}
	
	@Override
	public List<Country> findAllActive() throws DataAccessException {
		return repository.findByStatus(true);
	}
	
	@Override
	public Country findOneByName(String name) throws DataAccessException {
		return repository.findOneByName(name);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Country save(Country country) throws DataAccessException {
		return repository.save(country);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteById(Integer id) throws DataAccessException {
		repository.deleteById(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateState(Integer id, Boolean state) throws DataAccessException {
		repository.updateState(id, state);
	}
}
