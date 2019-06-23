package com.kdc.cnema.repositories;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kdc.cnema.domain.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer>{
	
	@Modifying
	@Query(value = "UPDATE horario SET state = :state WHERE id_horario = :id"
			, nativeQuery = true)
	public void updateState(@Param("id") Integer id, @Param("state") Boolean state) throws DataAccessException;
	
}
