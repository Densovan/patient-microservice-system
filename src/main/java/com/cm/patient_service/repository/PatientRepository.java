package com.cm.patient_service.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cm.patient_service.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient,UUID> {
    
}
