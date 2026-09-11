package com.hospital.management.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.hospital.management.dto.DepartmentDTO;
import com.hospital.management.service.DepartmentService;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public DepartmentDTO createDepartment(
            @RequestBody DepartmentDTO departmentDTO) {

        return departmentService.createDepartment(departmentDTO);
    }

    @GetMapping
    public List<DepartmentDTO> getAllDepartments() {

        return departmentService.getAllDepartments();
    }

    @GetMapping("/{id}")
    public DepartmentDTO getDepartmentById(
            @PathVariable Long id) {

        return departmentService.getDepartmentById(id);
    }

    @PutMapping("/{id}")
    public DepartmentDTO updateDepartment(
            @PathVariable Long id,
            @RequestBody DepartmentDTO departmentDTO) {

        return departmentService.updateDepartment(id, departmentDTO);
    }

    @DeleteMapping("/{id}")
    public String deleteDepartment(
            @PathVariable Long id) {

        departmentService.deleteDepartment(id);

        return "Department deleted successfully";
    }
}