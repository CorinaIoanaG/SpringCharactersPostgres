package com.accesa.demo.repository;

import com.accesa.demo.model.ActuatorResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActuatorRepository extends JpaRepository<ActuatorResponse, Long> {
}
