package com.kdc.cnema.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.kdc.cnema.domain.Schedule;


public interface ScheduleService {
	Schedule findOneById(Integer id) throws DataAccessException;
	
	List<Schedule> findAll() throws DataAccessException;
	
	Schedule save(Schedule user) throws DataAccessException;
	
	void deleteById(Integer id) throws DataAccessException;
	
	void updateState(Integer id, Boolean state) throws DataAccessException;
}
