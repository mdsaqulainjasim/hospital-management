package com.hospital.management.repository;

import com.hospital.management.entity.Admission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdmissionRepository extends JpaRepository<Admission, Long> {
}