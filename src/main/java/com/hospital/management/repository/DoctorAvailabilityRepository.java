package com.hospital.management.repository;

import com.hospital.management.entity.DoctorAvailability;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorAvailabilityRepository extends JpaRepository<DoctorAvailability, Long> {
    
}