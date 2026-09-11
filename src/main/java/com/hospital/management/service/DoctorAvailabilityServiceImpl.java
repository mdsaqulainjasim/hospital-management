package com.hospital.management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hospital.management.dto.DoctorAvailabilityDTO;
import com.hospital.management.entity.Doctor;
import com.hospital.management.entity.DoctorAvailability;
import com.hospital.management.exception.DoctorAvailabilityNotFoundException;
import com.hospital.management.exception.DoctorNotFoundException;
import com.hospital.management.repository.DoctorAvailabilityRepository;
import com.hospital.management.repository.DoctorRepository;

@Service
public class DoctorAvailabilityServiceImpl
        implements DoctorAvailabilityService {

    private final DoctorAvailabilityRepository availabilityRepository;
    private final DoctorRepository doctorRepository;

    public DoctorAvailabilityServiceImpl(DoctorAvailabilityRepository availabilityRepository,
            DoctorRepository doctorRepository) {

        this.availabilityRepository = availabilityRepository;
        this.doctorRepository = doctorRepository;
    }

    private DoctorAvailability convertToEntity(
            DoctorAvailabilityDTO availabilityDTO) {

        DoctorAvailability availability = new DoctorAvailability();

        availability.setDayOfWeek(availabilityDTO.getDayOfWeek());
        availability.setStartTime(availabilityDTO.getStartTime());
        availability.setEndTime(availabilityDTO.getEndTime());

        if (availabilityDTO.getDoctorId() != null) {

            Doctor doctor = doctorRepository.findById(
                    availabilityDTO.getDoctorId())
                    .orElseThrow(() -> new DoctorNotFoundException(
                            "Doctor not found with Id : "
                                    + availabilityDTO.getDoctorId()));

            availability.setDoctor(doctor);
        }

        return availability;
    }

    private DoctorAvailabilityDTO convertToDTO(DoctorAvailability availability) {

        DoctorAvailabilityDTO availabilityDTO = new DoctorAvailabilityDTO();

        availabilityDTO.setId(availability.getId());
        availabilityDTO.setDayOfWeek(availability.getDayOfWeek());
        availabilityDTO.setStartTime(availability.getStartTime());
        availabilityDTO.setEndTime(availability.getEndTime());

        if (availability.getDoctor() != null) {

            availabilityDTO.setDoctorId(
                    availability.getDoctor().getId());
        }

        return availabilityDTO;
    }

    @Override
    public DoctorAvailabilityDTO createAvailability(DoctorAvailabilityDTO availabilityDTO) {

        DoctorAvailability availability = convertToEntity(availabilityDTO);
        DoctorAvailability savedAvailability = availabilityRepository.save(availability);

        return convertToDTO(savedAvailability);
    }

    @Override
    public DoctorAvailabilityDTO getAvailabilityById(Long id) {

        Optional<DoctorAvailability> optionalAvailability = availabilityRepository.findById(id);

        if (optionalAvailability.isPresent()) {

            return convertToDTO(
                    optionalAvailability.get());
        }

        throw new DoctorAvailabilityNotFoundException(
                "Doctor availability not found with Id : " + id);
    }

    @Override
    public List<DoctorAvailabilityDTO> getAllAvailabilities() {

        List<DoctorAvailability> availabilities = availabilityRepository.findAll();

        return availabilities.stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public DoctorAvailabilityDTO updateAvailability(Long id, DoctorAvailabilityDTO availabilityDTO) {

        DoctorAvailability existingAvailability = availabilityRepository.findById(id)
                .orElseThrow(() -> new DoctorAvailabilityNotFoundException(
                        "Doctor availability not found with Id : " + id));

        existingAvailability.setDayOfWeek(availabilityDTO.getDayOfWeek());
        existingAvailability.setStartTime(availabilityDTO.getStartTime());
        existingAvailability.setEndTime(availabilityDTO.getEndTime());

        if (availabilityDTO.getDoctorId() != null) {

            Doctor doctor = doctorRepository.findById(
                    availabilityDTO.getDoctorId())
                    .orElseThrow(() -> new DoctorNotFoundException(
                            "Doctor not found with Id : "
                                    + availabilityDTO.getDoctorId()));

            existingAvailability.setDoctor(doctor);
        }

        DoctorAvailability updatedAvailability = availabilityRepository.save(existingAvailability);

        return convertToDTO(updatedAvailability);
    }

    @Override
    public void deleteAvailability(Long id) {

        DoctorAvailability existingAvailability = availabilityRepository.findById(id)
                .orElseThrow(() -> new DoctorAvailabilityNotFoundException(
                        "Doctor availability not found with Id : " + id));

        availabilityRepository.delete(existingAvailability);
    }
}