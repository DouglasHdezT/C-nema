package com.kdc.cnema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdc.cnema.domain.audit.MovieAudit;

public interface MovieAuditRepository extends JpaRepository<MovieAudit, Integer>{

}
