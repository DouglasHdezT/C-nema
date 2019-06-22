package com.kdc.cnema.repositories;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kdc.cnema.domain.Category;

/**
 * 
 * @author carlo
 * Repository de entidad category
 */
public interface CategoryRepository extends JpaRepository<Category, Integer>{
	Category findOneById(Integer id) throws DataAccessException;
}
