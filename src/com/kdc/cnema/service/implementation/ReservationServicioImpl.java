package com.kdc.cnema.service.implementation;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kdc.cnema.domain.Reservation;
import com.kdc.cnema.domain.Schedule;
import com.kdc.cnema.domain.User;
import com.kdc.cnema.repositories.ReservationRepository;
import com.kdc.cnema.service.ReservationService;
import com.kdc.cnema.service.ScheduleService;
import com.kdc.cnema.service.UserService;

@Service
public class ReservationServicioImpl implements ReservationService{
	
	@Autowired
	ReservationRepository seRepo;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ScheduleService scheduleService;

	@Override
	public Reservation findOneById(Integer id) throws DataAccessException {
		return seRepo.findById(id).get();
	}

	@Override
	public List<Reservation> findAll() throws DataAccessException {
		return seRepo.findAll();
	}
	
	@Override
	public List<Reservation> findAllPerUser(Integer id) throws DataAccessException {
		return seRepo.findAllPerUser(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Reservation save(Reservation reservation, Schedule schedule, User user) throws DataAccessException {
		
		schedule.setAvialable(
				schedule.getAvialable() - 
				reservation.getQuanReservations()
			);
		
		BigDecimal aux = new BigDecimal(
					
					user.getCurrCredit().doubleValue() - (
							reservation.getTotalPrice().doubleValue() - 
							reservation.getGrandTotal().doubleValue()
							)
				
				);
	
		user.setCurrCredit(aux);
		
		reservation.setRemainBalance(user.getCurrCredit());
				
		schedule = scheduleService.reserve(schedule);
		user =  userService.save(user);
		
		reservation.setSchedule(schedule);
		reservation.setUser(user);
		
		return seRepo.save(reservation);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteById(Integer id) throws DataAccessException {
		seRepo.deleteById(id);
	}

}

