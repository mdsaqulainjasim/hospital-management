package com.hospital.management.service;

import java.util.List;

import com.hospital.management.dto.AppointmentDTO;

public interface AppointmentService {

    AppointmentDTO createAppointment(AppointmentDTO appointmentDTO);

    AppointmentDTO getAppointmentById(Long id);

    List<AppointmentDTO> getAllAppointments();

    AppointmentDTO updateAppointment(Long id, AppointmentDTO appointmentDTO);

    void deleteAppointment(Long id);
}