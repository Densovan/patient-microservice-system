package com.cm.patient_service.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.cm.patient_service.dto.PatientRequestDTO;
import com.cm.patient_service.dto.PatientResponseDTO;
import com.cm.patient_service.exception.EmailAlreadyExistsException;
import com.cm.patient_service.exception.PatientNotFoundException;
import com.cm.patient_service.mapper.PatientMapper;
import com.cm.patient_service.model.Patient;
import com.cm.patient_service.repository.PatientRepository;

@Service
public class PatientService {
    private PatientRepository patientRepository;

    // dependency injection
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    // get patients
    public List<PatientResponseDTO> getPatients() {
        List<Patient> patients = patientRepository.findAll();

        // we can use two ways of code like this
        List<PatientResponseDTO> patientResponseDTOs = patients.stream().map(PatientMapper::toDTO).toList();
        // List<PatientResponseDTO> patientResponseDTOs = patients.stream().map(patient
        // -> PatientMapper.toDTO(patient)).toList();
        return patientResponseDTOs;

    }

    // create new patient
    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {
        if (patientRepository.existsByEmail(patientRequestDTO.getEmail())) {
            throw new EmailAlreadyExistsException(
                    "A patient with this email" + "already exist" +
                            patientRequestDTO.getEmail());
        }

        Patient newPatient = patientRepository.save(PatientMapper.toModel(patientRequestDTO));

        // an email address must be unique
        return PatientMapper.toDTO(newPatient);
    }

    // update patient
    public PatientResponseDTO updatePatient(UUID id, PatientRequestDTO patientRequestDTO) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found with ID:" + id));

        if (patientRepository.existsByEmailAndIdNot(patientRequestDTO.getEmail(), id)) {
            throw new EmailAlreadyExistsException(
                    "A patient with this email" + "already exist" +
                            patientRequestDTO.getEmail());
        }

        patient.setName(patientRequestDTO.getName());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));

        Patient updatePatient = patientRepository.save(patient);
        return PatientMapper.toDTO(updatePatient);
    }

}
