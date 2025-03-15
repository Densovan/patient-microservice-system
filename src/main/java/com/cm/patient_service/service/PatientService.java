package com.cm.patient_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cm.patient_service.dto.PatientResponseDTO;
import com.cm.patient_service.mapper.PatientMapper;
import com.cm.patient_service.model.Patient;
import com.cm.patient_service.repository.PatientRepository;

@Service
public class PatientService {
    private PatientRepository patientRepository;

    //dependency injection
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientResponseDTO> getPatients() {
        List<Patient> patients = patientRepository.findAll();

        // we can use two ways of code like this
        List<PatientResponseDTO> patientResponseDTOs = patients.stream().map(PatientMapper::toDTO).toList();
        // List<PatientResponseDTO> patientResponseDTOs = patients.stream().map(patient -> PatientMapper.toDTO(patient)).toList();
        return patientResponseDTOs;
        
    } 
}
