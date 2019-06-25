package com.kdc.cnema.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kdc.cnema.domain.Town;
import com.kdc.cnema.repositories.TownRepository;
import com.kdc.cnema.service.TownService;

@Service
public class TownServiceImpl implements TownService{
	
	@Autowired
	TownRepository tRepo;
	
	
	@Override
	public Town findOneById(Integer id) throws DataAccessException {
		return tRepo.findById(id).get();
	}

	@Override
	public List<Town> findAll() throws DataAccessException {
		return tRepo.findAll();
	}
	
	@Override
	public List<Town> findAllActive() throws DataAccessException {
		return tRepo.findByStatus(true);
	}
	
	@Override
	public Town findOneByName(String name) throws DataAccessException {
		return tRepo.findOneByName(name);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Town save(Town user) throws DataAccessException {
		return tRepo.save(user);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteById(Integer id) throws DataAccessException {
		tRepo.deleteById(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateState(Integer id, Boolean state) throws DataAccessException {
		tRepo.updateState(id, state);
	}
}
