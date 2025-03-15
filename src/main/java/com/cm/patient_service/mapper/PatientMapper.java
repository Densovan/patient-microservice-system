package com.cm.patient_service.mapper;

import com.cm.patient_service.dto.PatientResponseDTO;
import com.cm.patient_service.model.Patient;

public class PatientMapper {
    public static PatientResponseDTO toDTO(Patient patient) {
        PatientResponseDTO patientDTO = new PatientResponseDTO();
        patientDTO.setId(patient.getId().toString());
        patientDTO.setName(patient.getName().toString());
        patientDTO.setAddress(patient.getAddress().toString());
        patientDTO.setDateOfBirth(patient.getDateOfBirth().toString());
        
        return patientDTO;
    }
}
