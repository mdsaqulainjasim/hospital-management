package com.hospital.management.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.hospital.management.dto.AppointmentDTO;
import com.hospital.management.service.AppointmentService;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    public AppointmentDTO createAppointment(
            @RequestBody AppointmentDTO appointmentDTO) {

        return appointmentService.createAppointment(appointmentDTO);
    }

    @GetMapping
    public List<AppointmentDTO> getAllAppointments() {

        return appointmentService.getAllAppointments();
    }

    @GetMapping("/{id}")
    public AppointmentDTO getAppointmentById(
            @PathVariable Long id) {

        return appointmentService.getAppointmentById(id);
    }

    @PutMapping("/{id}")
    public AppointmentDTO updateAppointment(
            @PathVariable Long id,
            @RequestBody AppointmentDTO appointmentDTO) {

        return appointmentService.updateAppointment(
                id, appointmentDTO);
    }

    @DeleteMapping("/{id}")
    public String deleteAppointment(
            @PathVariable Long id) {

        appointmentService.deleteAppointment(id);

        return "Appointment deleted successfully";
    }
}