package com.hospital.management.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.hospital.management.dto.DoctorAvailabilityDTO;
import com.hospital.management.service.DoctorAvailabilityService;

@RestController
@RequestMapping("/api/doctor-availability")
public class DoctorAvailabilityController {

    private final DoctorAvailabilityService availabilityService;

    public DoctorAvailabilityController(
            DoctorAvailabilityService availabilityService) {

        this.availabilityService = availabilityService;
    }

    @PostMapping
    public DoctorAvailabilityDTO createAvailability(
            @RequestBody DoctorAvailabilityDTO availabilityDTO) {

        return availabilityService.createAvailability(availabilityDTO);
    }

    @GetMapping
    public List<DoctorAvailabilityDTO> getAllAvailabilities() {

        return availabilityService.getAllAvailabilities();
    }

    @GetMapping("/{id}")
    public DoctorAvailabilityDTO getAvailabilityById(
            @PathVariable Long id) {

        return availabilityService.getAvailabilityById(id);
    }

    @PutMapping("/{id}")
    public DoctorAvailabilityDTO updateAvailability(
            @PathVariable Long id,
            @RequestBody DoctorAvailabilityDTO availabilityDTO) {

        return availabilityService.updateAvailability(
                id, availabilityDTO);
    }

    @DeleteMapping("/{id}")
    public String deleteAvailability(
            @PathVariable Long id) {

        availabilityService.deleteAvailability(id);

        return "Doctor availability deleted successfully";
    }
}