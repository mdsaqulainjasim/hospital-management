package com.hospital.management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hospital.management.dto.PatientDTO;
import com.hospital.management.entity.Patient;
import com.hospital.management.entity.User;
import com.hospital.management.exception.PatientNotFoundException;
import com.hospital.management.exception.UserNotFoundException;
import com.hospital.management.repository.PatientRepository;
import com.hospital.management.repository.UserRepository;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final UserRepository userRepository;

    public PatientServiceImpl(
            PatientRepository patientRepository,
            UserRepository userRepository) {

        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
    }

    private Patient convertToEntity(PatientDTO patientDTO) {

        Patient patient = new Patient();

        patient.setBloodGroup(patientDTO.getBloodGroup());
        patient.setEmergencyContactName(
                patientDTO.getEmergencyContactName());
        patient.setEmergencyContactPhone(
                patientDTO.getEmergencyContactPhone());
        patient.setMedicalHistory(
                patientDTO.getMedicalHistory());
        patient.setAllergies(patientDTO.getAllergies());
        patient.setInsuranceNumber(
                patientDTO.getInsuranceNumber());

        if (patientDTO.getUserId() != null) {

            User user = userRepository.findById(
                    patientDTO.getUserId())
                    .orElseThrow(() -> new UserNotFoundException(
                            "User not found with Id : "
                                    + patientDTO.getUserId()));

            patient.setUser(user);
        }

        return patient;
    }

    private PatientDTO convertToDTO(Patient patient) {

        PatientDTO patientDTO = new PatientDTO();

        patientDTO.setId(patient.getId());
        patientDTO.setBloodGroup(patient.getBloodGroup());
        patientDTO.setEmergencyContactName(
                patient.getEmergencyContactName());
        patientDTO.setEmergencyContactPhone(
                patient.getEmergencyContactPhone());
        patientDTO.setMedicalHistory(
                patient.getMedicalHistory());
        patientDTO.setAllergies(patient.getAllergies());
        patientDTO.setInsuranceNumber(
                patient.getInsuranceNumber());

        if (patient.getUser() != null) {
            patientDTO.setUserId(
                    patient.getUser().getId());
        }

        return patientDTO;
    }

    @Override
    public PatientDTO createPatient(PatientDTO patientDTO) {

        Patient patient = convertToEntity(patientDTO);

        Patient savedPatient = patientRepository.save(patient);

        return convertToDTO(savedPatient);
    }

    @Override
    public PatientDTO getPatientById(Long id) {

        Optional<Patient> optionalPatient = patientRepository.findById(id);

        if (optionalPatient.isPresent()) {
            return convertToDTO(optionalPatient.get());
        }

        throw new PatientNotFoundException(
                "Patient not found with Id : " + id);
    }

    @Override
    public List<PatientDTO> getAllPatients() {

        List<Patient> patients = patientRepository.findAll();

        return patients.stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public PatientDTO updatePatient(
            Long id, PatientDTO patientDTO) {

        Patient existingPatient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException(
                        "Patient not found with Id : "
                                + id));

        existingPatient.setBloodGroup(
                patientDTO.getBloodGroup());

        existingPatient.setEmergencyContactName(
                patientDTO.getEmergencyContactName());

        existingPatient.setEmergencyContactPhone(
                patientDTO.getEmergencyContactPhone());

        existingPatient.setMedicalHistory(
                patientDTO.getMedicalHistory());

        existingPatient.setAllergies(
                patientDTO.getAllergies());

        existingPatient.setInsuranceNumber(
                patientDTO.getInsuranceNumber());

        if (patientDTO.getUserId() != null) {

            User user = userRepository.findById(
                    patientDTO.getUserId())
                    .orElseThrow(() -> new UserNotFoundException(
                            "User not found with Id : "
                                    + patientDTO.getUserId()));

            existingPatient.setUser(user);
        }

        Patient updatedPatient = patientRepository.save(existingPatient);

        return convertToDTO(updatedPatient);
    }

    @Override
    public void deletePatient(Long id) {

        Patient existingPatient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException(
                        "Patient not found with Id : "
                                + id));

        patientRepository.delete(existingPatient);
    }
}