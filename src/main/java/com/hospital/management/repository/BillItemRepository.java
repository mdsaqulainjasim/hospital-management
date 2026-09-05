package com.hospital.management.repository;

import com.hospital.management.entity.BillItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillItemRepository extends JpaRepository<BillItem, Long> {
}