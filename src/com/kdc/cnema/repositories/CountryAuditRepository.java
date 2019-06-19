package com.kdc.cnema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdc.cnema.domain.audit.CountryAudit;

public interface CountryAuditRepository extends JpaRepository<CountryAudit, Integer>{

}
