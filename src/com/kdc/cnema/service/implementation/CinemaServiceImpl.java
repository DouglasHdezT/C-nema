package com.kdc.cnema.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kdc.cnema.domain.Cinema;
import com.kdc.cnema.repositories.CinemaRepository;
import com.kdc.cnema.service.CinemaService;

@Service
public class CinemaServiceImpl implements CinemaService{
	
	@Autowired
	private CinemaRepository cinemaRepo;
	
	@Override
	public Cinema findOneById(Integer id) throws DataAccessException {
		return cinemaRepo.findById(id).get();
	}

	@Override
	public List<Cinema> findAll() throws DataAccessException {
		return cinemaRepo.findAll();
	}
	
	@Override
	public List<Cinema> findAllActive() throws DataAccessException {
		return cinemaRepo.findByStatus(true);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Cinema save(Cinema cinema) throws DataAccessException {
		return cinemaRepo.save(cinema);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteById(Integer id) throws DataAccessException {
		cinemaRepo.deleteById(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateState(Integer id, Boolean state) throws DataAccessException {
		cinemaRepo.updateState(id, state);
	}

}
