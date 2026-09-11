package com.hospital.management.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.hospital.management.dto.StaffDTO;
import com.hospital.management.service.StaffService;

@RestController
@RequestMapping("/api/staff")
public class StaffController {

    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @PostMapping
    public StaffDTO createStaff(
            @RequestBody StaffDTO staffDTO) {

        return staffService.createStaff(staffDTO);
    }

    @GetMapping
    public List<StaffDTO> getAllStaff() {

        return staffService.getAllStaff();
    }

    @GetMapping("/{id}")
    public StaffDTO getStaffById(
            @PathVariable Long id) {

        return staffService.getStaffById(id);
    }

    @PutMapping("/{id}")
    public StaffDTO updateStaff(
            @PathVariable Long id,
            @RequestBody StaffDTO staffDTO) {

        return staffService.updateStaff(id, staffDTO);
    }

    @DeleteMapping("/{id}")
    public String deleteStaff(
            @PathVariable Long id) {

        staffService.deleteStaff(id);

        return "Staff deleted successfully";
    }
}