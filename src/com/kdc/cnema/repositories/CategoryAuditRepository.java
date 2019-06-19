package com.kdc.cnema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdc.cnema.domain.audit.CategoryAudit;

public interface CategoryAuditRepository extends JpaRepository<CategoryAudit, Integer>{

}
