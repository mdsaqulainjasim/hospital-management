package com.hospital.management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hospital.management.dto.DepartmentDTO;
import com.hospital.management.entity.Department;
import com.hospital.management.exception.DepartmentNotFoundException;
import com.hospital.management.repository.DepartmentRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    private Department convertToEntity(DepartmentDTO departmentDTO) {

        Department department = new Department();

        department.setName(departmentDTO.getName());
        department.setDescription(departmentDTO.getDescription());

        return department;
    }

    private DepartmentDTO convertToDTO(Department department) {

        DepartmentDTO departmentDTO = new DepartmentDTO();

        departmentDTO.setId(department.getId());
        departmentDTO.setName(department.getName());
        departmentDTO.setDescription(department.getDescription());

        return departmentDTO;
    }

    @Override
    public DepartmentDTO createDepartment(DepartmentDTO departmentDTO) {

        Department department = convertToEntity(departmentDTO);

        Department savedDepartment = departmentRepository.save(department);

        return convertToDTO(savedDepartment);
    }

    @Override
    public DepartmentDTO getDepartmentById(Long id) {

        Optional<Department> optionalDepartment =
                departmentRepository.findById(id);

        if (optionalDepartment.isPresent()) {
            return convertToDTO(optionalDepartment.get());
        }

        throw new DepartmentNotFoundException(
                "Department not found with Id : " + id);
    }

    @Override
    public List<DepartmentDTO> getAllDepartments() {

        List<Department> departments = departmentRepository.findAll();

        return departments.stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public DepartmentDTO updateDepartment(
            Long id, DepartmentDTO departmentDTO) {

        Department existingDepartment =
                departmentRepository.findById(id)
                        .orElseThrow(() ->
                                new DepartmentNotFoundException(
                                        "Department not found with Id : " + id));

        existingDepartment.setName(departmentDTO.getName());
        existingDepartment.setDescription(departmentDTO.getDescription());

        Department updatedDepartment =
                departmentRepository.save(existingDepartment);

        return convertToDTO(updatedDepartment);
    }

    @Override
    public void deleteDepartment(Long id) {

        Department existingDepartment =
                departmentRepository.findById(id)
                        .orElseThrow(() ->
                                new DepartmentNotFoundException(
                                        "Department not found with Id : " + id));

        departmentRepository.delete(existingDepartment);
    }
}