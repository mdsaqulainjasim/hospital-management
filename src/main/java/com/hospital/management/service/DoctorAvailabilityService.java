package com.hospital.management.service;

import java.util.List;

import com.hospital.management.dto.DoctorAvailabilityDTO;

public interface DoctorAvailabilityService {

    DoctorAvailabilityDTO createAvailability(DoctorAvailabilityDTO availabilityDTO);

    DoctorAvailabilityDTO getAvailabilityById(Long id);

    List<DoctorAvailabilityDTO> getAllAvailabilities();

    DoctorAvailabilityDTO updateAvailability(Long id, DoctorAvailabilityDTO availabilityDTO);

    void deleteAvailability(Long id);
}