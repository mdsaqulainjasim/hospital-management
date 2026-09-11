package com.hospital.management.service;

import java.util.List;

import com.hospital.management.dto.PatientDTO;

public interface PatientService {

    PatientDTO createPatient(PatientDTO patientDTO);

    PatientDTO getPatientById(Long id);

    List<PatientDTO> getAllPatients();

    PatientDTO updatePatient(Long id, PatientDTO patientDTO);

    void deletePatient(Long id);
}