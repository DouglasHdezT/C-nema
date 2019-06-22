package com.kdc.cnema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdc.cnema.domain.Country;

/**
 * 
 * @author carlo
 *	Repositorio de entidad Country
 */
public interface CountryRepository extends JpaRepository<Country, Integer>{
	
	public Country findOneById(Integer id);
	
}
