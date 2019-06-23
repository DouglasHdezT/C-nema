package com.kdc.cnema.repositories;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;


import com.kdc.cnema.domain.Town;

public interface TownRepository extends JpaRepository<Town, Integer>{

	public Town findOneByName(String name) throws DataAccessException;
}
