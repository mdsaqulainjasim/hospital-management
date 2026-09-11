package com.hospital.management.dto;

import java.time.LocalDateTime;

import com.hospital.management.entity.AppointmentStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AppointmentDTO {

    private Long id;

    private Long patientId;

    private Long doctorId;

    private LocalDateTime appointmentDateTime;

    private String reason;

    private AppointmentStatus status;
}