package com.kdc.cnema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kdc.cnema.domain.audit.ProfileAudit;

public interface ProfileAuditRepository extends JpaRepository<ProfileAudit, Integer>{

	
	@Query(value = "SELECT * FROM cuentas_auditoria WHERE id_usuario = :user_id"
			, nativeQuery = true)
	public ProfileAudit findOneByUserId(@Param("user_id") Integer id);
	
}
