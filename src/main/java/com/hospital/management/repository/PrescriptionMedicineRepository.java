package com.hospital.management.repository;

import com.hospital.management.entity.PrescriptionMedicine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionMedicineRepository extends JpaRepository<PrescriptionMedicine, Long> {
}