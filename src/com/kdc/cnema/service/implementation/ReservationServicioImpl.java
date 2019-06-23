package com.kdc.cnema.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kdc.cnema.domain.Reservation;
import com.kdc.cnema.repositories.ReservationRepository;
import com.kdc.cnema.service.ReservationService;

@Service
public class ReservationServicioImpl implements ReservationService{
	
	@Autowired
	ReservationRepository seRepo;

	@Override
	public Reservation findOneById(Integer id) throws DataAccessException {
		// TODO Auto-generated method stub
		return seRepo.findById(id).get();
	}

	@Override
	public List<Reservation> findAll() throws DataAccessException {
		// TODO Auto-generated method stub
		return seRepo.findAll();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Reservation save(Reservation user) throws DataAccessException {
		// TODO Auto-generated method stub
		return seRepo.save(user);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteById(Integer id) throws DataAccessException {
		// TODO Auto-generated method stub
		seRepo.deleteById(id);
	}

}

