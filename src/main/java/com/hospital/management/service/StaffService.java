package com.hospital.management.service;

import java.util.List;

import com.hospital.management.dto.StaffDTO;

public interface StaffService {

    StaffDTO createStaff(StaffDTO staffDTO);

    StaffDTO getStaffById(Long id);

    List<StaffDTO> getAllStaff();

    StaffDTO updateStaff(Long id, StaffDTO staffDTO);

    void deleteStaff(Long id);
}