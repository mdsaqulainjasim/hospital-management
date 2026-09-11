package com.hospital.management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hospital.management.dto.AppointmentDTO;
import com.hospital.management.entity.Appointment;
import com.hospital.management.entity.Doctor;
import com.hospital.management.entity.Patient;
import com.hospital.management.exception.AppointmentNotFoundException;
import com.hospital.management.exception.DoctorNotFoundException;
import com.hospital.management.exception.PatientNotFoundException;
import com.hospital.management.repository.AppointmentRepository;
import com.hospital.management.repository.DoctorRepository;
import com.hospital.management.repository.PatientRepository;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public AppointmentServiceImpl(
            AppointmentRepository appointmentRepository,
            PatientRepository patientRepository,
            DoctorRepository doctorRepository) {

        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    private Appointment convertToEntity(
            AppointmentDTO appointmentDTO) {

        Appointment appointment = new Appointment();

        appointment.setAppointmentDateTime(
                appointmentDTO.getAppointmentDateTime());

        appointment.setReason(
                appointmentDTO.getReason());

        appointment.setStatus(
                appointmentDTO.getStatus());

        if (appointmentDTO.getPatientId() != null) {

            Patient patient = patientRepository.findById(
                    appointmentDTO.getPatientId())
                    .orElseThrow(() ->
                            new PatientNotFoundException(
                                    "Patient not found with Id : "
                                            + appointmentDTO.getPatientId()));

            appointment.setPatient(patient);
        }

        if (appointmentDTO.getDoctorId() != null) {

            Doctor doctor = doctorRepository.findById(
                    appointmentDTO.getDoctorId())
                    .orElseThrow(() ->
                            new DoctorNotFoundException(
                                    "Doctor not found with Id : "
                                            + appointmentDTO.getDoctorId()));

            appointment.setDoctor(doctor);
        }

        return appointment;
    }

    private AppointmentDTO convertToDTO(
            Appointment appointment) {

        AppointmentDTO appointmentDTO =
                new AppointmentDTO();

        appointmentDTO.setId(appointment.getId());

        appointmentDTO.setAppointmentDateTime(
                appointment.getAppointmentDateTime());

        appointmentDTO.setReason(
                appointment.getReason());

        appointmentDTO.setStatus(
                appointment.getStatus());

        if (appointment.getPatient() != null) {

            appointmentDTO.setPatientId(
                    appointment.getPatient().getId());
        }

        if (appointment.getDoctor() != null) {

            appointmentDTO.setDoctorId(
                    appointment.getDoctor().getId());
        }

        return appointmentDTO;
    }

    @Override
    public AppointmentDTO createAppointment(
            AppointmentDTO appointmentDTO) {

        Appointment appointment =
                convertToEntity(appointmentDTO);

        Appointment savedAppointment =
                appointmentRepository.save(appointment);

        return convertToDTO(savedAppointment);
    }

    @Override
    public AppointmentDTO getAppointmentById(Long id) {

        Optional<Appointment> optionalAppointment =
                appointmentRepository.findById(id);

        if (optionalAppointment.isPresent()) {

            return convertToDTO(
                    optionalAppointment.get());
        }

        throw new AppointmentNotFoundException(
                "Appointment not found with Id : " + id);
    }

    @Override
    public List<AppointmentDTO> getAllAppointments() {

        List<Appointment> appointments =
                appointmentRepository.findAll();

        return appointments.stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public AppointmentDTO updateAppointment(
            Long id,
            AppointmentDTO appointmentDTO) {

        Appointment existingAppointment =
                appointmentRepository.findById(id)
                        .orElseThrow(() ->
                                new AppointmentNotFoundException(
                                        "Appointment not found with Id : "
                                                + id));

        existingAppointment.setAppointmentDateTime(
                appointmentDTO.getAppointmentDateTime());

        existingAppointment.setReason(
                appointmentDTO.getReason());

        existingAppointment.setStatus(
                appointmentDTO.getStatus());

        if (appointmentDTO.getPatientId() != null) {

            Patient patient = patientRepository.findById(
                    appointmentDTO.getPatientId())
                    .orElseThrow(() ->
                            new PatientNotFoundException(
                                    "Patient not found with Id : "
                                            + appointmentDTO.getPatientId()));

            existingAppointment.setPatient(patient);
        }

        if (appointmentDTO.getDoctorId() != null) {

            Doctor doctor = doctorRepository.findById(
                    appointmentDTO.getDoctorId())
                    .orElseThrow(() ->
                            new DoctorNotFoundException(
                                    "Doctor not found with Id : "
                                            + appointmentDTO.getDoctorId()));

            existingAppointment.setDoctor(doctor);
        }

        Appointment updatedAppointment =
                appointmentRepository.save(existingAppointment);

        return convertToDTO(updatedAppointment);
    }

    @Override
    public void deleteAppointment(Long id) {

        Appointment existingAppointment =
                appointmentRepository.findById(id)
                        .orElseThrow(() ->
                                new AppointmentNotFoundException(
                                        "Appointment not found with Id : "
                                                + id));

        appointmentRepository.delete(existingAppointment);
    }
}