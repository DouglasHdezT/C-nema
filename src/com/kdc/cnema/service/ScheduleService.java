package com.kdc.cnema.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.kdc.cnema.domain.Schedule;


public interface ScheduleService {
	Schedule findOneById(Integer id) throws DataAccessException;
	
	List<Schedule> findAll() throws DataAccessException;
	
	List<Schedule> findAllActive() throws DataAccessException;
	
	List<Schedule> findAllPerMovie(Integer id) throws DataAccessException;
	
	Schedule save(Schedule schedule, String username) throws DataAccessException;
	
	Schedule reserve(Schedule schedule) throws DataAccessException;
	
	void deleteById(Integer id) throws DataAccessException;
	
	void updateState(Integer id, Boolean state, String username) throws DataAccessException;
}
