package com.kdc.cnema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdc.cnema.domain.Reservation;

/**
 * 
 * @author carlo
 *Repositorio de reservacion
 */
public interface ReservationRepository extends JpaRepository<Reservation, Integer>{
	
}
