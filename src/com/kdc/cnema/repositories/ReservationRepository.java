package com.kdc.cnema.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kdc.cnema.domain.Reservation;

/**
 * 
 * @author carlo
 *Repositorio de reservacion
 */
public interface ReservationRepository extends JpaRepository<Reservation, Integer>{
	
	@Query(value = "SELECT * FROM reservacion WHERE id_usuario = :id"
			, nativeQuery = true)
	public List<Reservation> findAllPerUser(@Param("id") Integer id);
	
}
