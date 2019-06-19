package com.kdc.cnema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdc.cnema.domain.Cinema;
/**
 * 
 * @author carlo
 *	Repository para entidad Sala
 */
public interface CinemaRepository extends JpaRepository<Cinema, Integer>{

}
