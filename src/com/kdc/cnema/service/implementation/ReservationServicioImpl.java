package com.kdc.cnema.service.implementation;

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
	public Reservation save(Reservation reservation, Schedule schedule, User user) throws DataAccessException {
		
		schedule.setAvialable(
				schedule.getAvialable() - 
				reservation.getQuanReservations()
			);
	
		user.setCurrCredit(
					user.getCurrCredit() - 
					reservation.getUsedBalance()
				);;
		
		reservation.setRemainBalance(user.getCurrCredit());
				
		schedule = scheduleService.save(schedule);
		user =  userService.save(user);
		
		reservation.setSchedule(schedule);
		reservation.setUser(user);
		
		return seRepo.save(reservation);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteById(Integer id) throws DataAccessException {
		// TODO Auto-generated method stub
		seRepo.deleteById(id);
	}

}

