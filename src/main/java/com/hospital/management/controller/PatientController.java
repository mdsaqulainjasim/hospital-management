package com.hospital.management.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.hospital.management.dto.PatientDTO;
import com.hospital.management.service.PatientService;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public PatientDTO createPatient(
            @RequestBody PatientDTO patientDTO) {

        return patientService.createPatient(patientDTO);
    }

    @GetMapping
    public List<PatientDTO> getAllPatients() {

        return patientService.getAllPatients();
    }

    @GetMapping("/{id}")
    public PatientDTO getPatientById(
            @PathVariable Long id) {

        return patientService.getPatientById(id);
    }

    @PutMapping("/{id}")
    public PatientDTO updatePatient(
            @PathVariable Long id,
            @RequestBody PatientDTO patientDTO) {

        return patientService.updatePatient(id, patientDTO);
    }

    @DeleteMapping("/{id}")
    public String deletePatient(
            @PathVariable Long id) {

        patientService.deletePatient(id);

        return "Patient deleted successfully";
    }
}