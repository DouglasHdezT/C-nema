package com.kdc.cnema.repositories;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kdc.cnema.domain.Category;

/**
 * 
 * @author carlo
 * Repository de entidad category
 */
public interface CategoryRepository extends JpaRepository<Category, Integer>{
	
	public Category findOneById(Integer id) throws DataAccessException;
	
	public Category findOneByName(String name) throws DataAccessException;
	
	@Modifying
	@Query(value = "UPDATE categoria SET status = :status WHERE id_categoria = :id"
			, nativeQuery = true)
	public void updateState(@Param("id") Integer id, @Param("status") Boolean status) throws DataAccessException;
	
	public List<Category> findByStatus(Boolean status);
}
