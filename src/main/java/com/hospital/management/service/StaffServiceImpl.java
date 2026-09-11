package com.hospital.management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hospital.management.dto.StaffDTO;
import com.hospital.management.entity.Department;
import com.hospital.management.entity.Staff;
import com.hospital.management.entity.User;
import com.hospital.management.exception.DepartmentNotFoundException;
import com.hospital.management.exception.StaffNotFoundException;
import com.hospital.management.exception.UserNotFoundException;
import com.hospital.management.repository.DepartmentRepository;
import com.hospital.management.repository.StaffRepository;
import com.hospital.management.repository.UserRepository;

@Service
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;
    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;

    public StaffServiceImpl(StaffRepository staffRepository, UserRepository userRepository,
            DepartmentRepository departmentRepository) {

        this.staffRepository = staffRepository;
        this.userRepository = userRepository;
        this.departmentRepository = departmentRepository;
    }

    private Staff convertToEntity(StaffDTO staffDTO) {

        Staff staff = new Staff();

        staff.setDesignation(staffDTO.getDesignation());
        staff.setJoiningDate(staffDTO.getJoiningDate());
        staff.setSalary(staffDTO.getSalary());
        staff.setStatus(staffDTO.getStatus());

        if (staffDTO.getUserId() != null) {

            User user = userRepository.findById(
                    staffDTO.getUserId())
                    .orElseThrow(() -> new UserNotFoundException(
                            "User not found with Id : "
                                    + staffDTO.getUserId()));

            staff.setUser(user);
        }

        if (staffDTO.getDepartmentId() != null) {

            Department department = departmentRepository.findById(
                    staffDTO.getDepartmentId())
                    .orElseThrow(() -> new DepartmentNotFoundException(
                            "Department not found with Id : "
                                    + staffDTO.getDepartmentId()));

            staff.setDepartment(department);
        }

        return staff;
    }

    private StaffDTO convertToDTO(Staff staff) {

        StaffDTO staffDTO = new StaffDTO();

        staffDTO.setId(staff.getId());
        staffDTO.setDesignation(staff.getDesignation());
        staffDTO.setJoiningDate(staff.getJoiningDate());
        staffDTO.setSalary(staff.getSalary());
        staffDTO.setStatus(staff.getStatus());

        if (staff.getUser() != null) {
            staffDTO.setUserId(
                    staff.getUser().getId());
        }

        if (staff.getDepartment() != null) {
            staffDTO.setDepartmentId(
                    staff.getDepartment().getId());
        }

        return staffDTO;
    }

    @Override
    public StaffDTO createStaff(StaffDTO staffDTO) {

        Staff staff = convertToEntity(staffDTO);
        Staff savedStaff = staffRepository.save(staff);
        return convertToDTO(savedStaff);
    }

    @Override
    public StaffDTO getStaffById(Long id) {

        Optional<Staff> optionalStaff = staffRepository.findById(id);

        if (optionalStaff.isPresent()) {
            return convertToDTO(optionalStaff.get());
        }

        throw new StaffNotFoundException(
                "Staff not found with Id : " + id);
    }

    @Override
    public List<StaffDTO> getAllStaff() {

        List<Staff> staffList = staffRepository.findAll();

        return staffList.stream().map(this::convertToDTO).toList();
    }

    @Override
    public StaffDTO updateStaff(
            Long id, StaffDTO staffDTO) {

        Staff existingStaff = staffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Staff not found with Id : " + id));

        existingStaff.setDesignation(staffDTO.getDesignation());
        existingStaff.setJoiningDate(staffDTO.getJoiningDate());
        existingStaff.setSalary(staffDTO.getSalary());
        existingStaff.setStatus(staffDTO.getStatus());

        if (staffDTO.getUserId() != null) {

            User user = userRepository.findById(
                    staffDTO.getUserId())
                    .orElseThrow(() -> new StaffNotFoundException(
                            "Staff not found with Id : " + id));

            existingStaff.setUser(user);
        }

        if (staffDTO.getDepartmentId() != null) {

            Department department = departmentRepository.findById(
                    staffDTO.getDepartmentId())
                    .orElseThrow(() -> new DepartmentNotFoundException(
                            "Department not found with Id : " + staffDTO.getDepartmentId()));

            existingStaff.setDepartment(department);
        }

        Staff updatedStaff = staffRepository.save(existingStaff);
        return convertToDTO(updatedStaff);
    }

    @Override
    public void deleteStaff(Long id) {

        Staff existingStaff = staffRepository.findById(id)
                .orElseThrow(() -> new StaffNotFoundException(
                        "Staff not found with Id : " + id));

        staffRepository.delete(existingStaff);
    }
}