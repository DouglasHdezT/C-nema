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
		// TODO Auto-generated method stub
		return tRepo.findById(id).get();
	}

	@Override
	public List<Town> findAll() throws DataAccessException {
		// TODO Auto-generated method stub
		return tRepo.findAll();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Town save(Town user) throws DataAccessException {
		// TODO Auto-generated method stub
		return tRepo.save(user);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteById(Integer id) throws DataAccessException {
		// TODO Auto-generated method stub
		tRepo.deleteById(id);
	}

	@Override
	public Town findOneByName(String name) throws DataAccessException {
		// TODO Auto-generated method stub
		return tRepo.findOneByName(name);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateState(Integer id, Boolean state) throws DataAccessException {
		tRepo.updateState(id, state);
	}
}
