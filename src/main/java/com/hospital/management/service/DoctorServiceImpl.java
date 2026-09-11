package com.hospital.management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hospital.management.dto.DoctorDTO;
import com.hospital.management.entity.Department;
import com.hospital.management.entity.Doctor;
import com.hospital.management.entity.User;
import com.hospital.management.exception.DepartmentNotFoundException;
import com.hospital.management.exception.DoctorNotFoundException;
import com.hospital.management.exception.UserNotFoundException;
import com.hospital.management.repository.DepartmentRepository;
import com.hospital.management.repository.DoctorRepository;
import com.hospital.management.repository.UserRepository;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;

    public DoctorServiceImpl(
            DoctorRepository doctorRepository,
            UserRepository userRepository,
            DepartmentRepository departmentRepository) {

        this.doctorRepository = doctorRepository;
        this.userRepository = userRepository;
        this.departmentRepository = departmentRepository;
    }

    private Doctor convertToEntity(DoctorDTO doctorDTO) {

        Doctor doctor = new Doctor();

        doctor.setSpecialization(doctorDTO.getSpecialization());
        doctor.setLicenseNumber(doctorDTO.getLicenseNumber());
        doctor.setExperienceYears(doctorDTO.getExperienceYears());
        doctor.setQualification(doctorDTO.getQualification());
        doctor.setConsultationFee(doctorDTO.getConsultationFee());
        doctor.setStatus(doctorDTO.getStatus());

        if (doctorDTO.getUserId() != null) {

            User user = userRepository.findById(doctorDTO.getUserId())
                    .orElseThrow(() -> new UserNotFoundException(
                            "User not found with Id : "
                                    + doctorDTO.getUserId()));

            doctor.setUser(user);
        }

        if (doctorDTO.getDepartmentId() != null) {

            Department department = departmentRepository.findById(doctorDTO.getDepartmentId())
                    .orElseThrow(() -> new DepartmentNotFoundException(
                            "Department not found with Id : "
                                    + doctorDTO.getDepartmentId()));

            doctor.setDepartment(department);
        }

        return doctor;
    }

    private DoctorDTO convertToDTO(Doctor doctor) {

        DoctorDTO doctorDTO = new DoctorDTO();

        doctorDTO.setId(doctor.getId());
        doctorDTO.setSpecialization(doctor.getSpecialization());
        doctorDTO.setLicenseNumber(doctor.getLicenseNumber());
        doctorDTO.setExperienceYears(doctor.getExperienceYears());
        doctorDTO.setQualification(doctor.getQualification());
        doctorDTO.setConsultationFee(doctor.getConsultationFee());
        doctorDTO.setStatus(doctor.getStatus());

        if (doctor.getUser() != null) {
            doctorDTO.setUserId(doctor.getUser().getId());
        }

        if (doctor.getDepartment() != null) {
            doctorDTO.setDepartmentId(doctor.getDepartment().getId());
        }

        return doctorDTO;
    }

    @Override
    public DoctorDTO createDoctor(DoctorDTO doctorDTO) {

        Doctor doctor = convertToEntity(doctorDTO);

        Doctor savedDoctor = doctorRepository.save(doctor);

        return convertToDTO(savedDoctor);
    }

    @Override
    public DoctorDTO getDoctorById(Long id) {

        Optional<Doctor> optionalDoctor = doctorRepository.findById(id);

        if (optionalDoctor.isPresent()) {
            return convertToDTO(optionalDoctor.get());
        }

        throw new DoctorNotFoundException(
                "Doctor not found with Id : " + id);
    }

    @Override
    public List<DoctorDTO> getAllDoctors() {

        List<Doctor> doctors = doctorRepository.findAll();

        return doctors.stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public DoctorDTO updateDoctor(
            Long id, DoctorDTO doctorDTO) {

        Doctor existingDoctor = doctorRepository.findById(id)
                .orElseThrow(() -> new DoctorNotFoundException(
                        "Doctor not found with Id : " + id));

        existingDoctor.setSpecialization(
                doctorDTO.getSpecialization());

        existingDoctor.setLicenseNumber(
                doctorDTO.getLicenseNumber());

        existingDoctor.setExperienceYears(
                doctorDTO.getExperienceYears());

        existingDoctor.setQualification(
                doctorDTO.getQualification());

        existingDoctor.setConsultationFee(
                doctorDTO.getConsultationFee());

        existingDoctor.setStatus(
                doctorDTO.getStatus());

        if (doctorDTO.getUserId() != null) {

            User user = userRepository.findById(
                    doctorDTO.getUserId())
                    .orElseThrow(() -> new UserNotFoundException(
                            "User not found with Id : "
                                    + doctorDTO.getUserId()));

            existingDoctor.setUser(user);
        }

        if (doctorDTO.getDepartmentId() != null) {

            Department department = departmentRepository.findById(
                    doctorDTO.getDepartmentId())
                    .orElseThrow(() -> new DepartmentNotFoundException(
                            "Department not found with Id : "
                                    + doctorDTO.getDepartmentId()));

            existingDoctor.setDepartment(department);
        }

        Doctor updatedDoctor = doctorRepository.save(existingDoctor);

        return convertToDTO(updatedDoctor);
    }

    @Override
    public void deleteDoctor(Long id) {

        Doctor existingDoctor = doctorRepository.findById(id)
                .orElseThrow(() -> new DoctorNotFoundException(
                        "Doctor not found with Id : " + id));

        doctorRepository.delete(existingDoctor);
    }
}