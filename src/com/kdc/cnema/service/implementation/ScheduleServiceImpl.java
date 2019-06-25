package com.kdc.cnema.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kdc.cnema.domain.Schedule;
import com.kdc.cnema.repositories.ScheduleRepository;
import com.kdc.cnema.service.ScheduleService;

@Service
public class ScheduleServiceImpl implements ScheduleService{
	
	@Autowired
	ScheduleRepository sRepo;

	@Override
	public Schedule findOneById(Integer id) throws DataAccessException {
		// TODO Auto-generated method stub
		return sRepo.findById(id).get();
	}

	@Override
	public List<Schedule> findAll() throws DataAccessException {
		// TODO Auto-generated method stub
		return sRepo.findAll();
	}
	
	@Override
	public List<Schedule> findAllActive() throws DataAccessException {
		// TODO Auto-generated method stub
		return sRepo.findByStatus(true);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Schedule save(Schedule user) throws DataAccessException {
		// TODO Auto-generated method stub
		return sRepo.save(user);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteById(Integer id) throws DataAccessException {
		// TODO Auto-generated method stub
		sRepo.deleteById(id);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateState(Integer id, Boolean state) throws DataAccessException {
		sRepo.updateState(id, state);
	}

}
