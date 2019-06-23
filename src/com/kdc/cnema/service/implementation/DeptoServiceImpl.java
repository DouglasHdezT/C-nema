package com.kdc.cnema.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kdc.cnema.domain.Depto;
import com.kdc.cnema.repositories.DeptoRepository;
import com.kdc.cnema.service.DeptoService;

@Service
public class DeptoServiceImpl implements DeptoService{
	
	@Autowired
	private DeptoRepository deptoRepo;
	
	@Override
	public Depto findOneById(Integer id) throws DataAccessException {
		return deptoRepo.findById(id).get();
	}

	@Override
	public List<Depto> findAll() throws DataAccessException {
		return deptoRepo.findAll();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Depto save(Depto depto) throws DataAccessException {
		return deptoRepo.save(depto);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteById(Integer id) throws DataAccessException {
		deptoRepo.deleteById(id);
		
	}

	@Override
	public Depto findOneByName(String name) throws DataAccessException {
		// TODO Auto-generated method stub
		return deptoRepo.findOneByName(name);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateState(Integer id, Boolean state) throws DataAccessException {
		deptoRepo.updateState(id, state);
	}

}
