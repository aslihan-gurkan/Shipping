package com.management.shipping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.shipping.model.ErrorLog;

public interface LogRepository extends JpaRepository<ErrorLog, Long>{

}
