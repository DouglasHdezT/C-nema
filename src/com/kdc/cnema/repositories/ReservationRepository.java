package com.kdc.cnema.repositories;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kdc.cnema.domain.Reservation;

/**
 * 
 * @author carlo
 *Repositorio de reservacion
 */
public interface ReservationRepository extends JpaRepository<Reservation, Integer>{
	
	@Modifying
	@Query(value = "UPDATE categoria SET state = :state WHERE id_categoria = :id"
			, nativeQuery = true)
	public void updateState(@Param("id") Integer id, @Param("state") Boolean state) throws DataAccessException;
	
}
