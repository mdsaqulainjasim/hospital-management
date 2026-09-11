package com.hospital.management.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatientDTO {

    private Long id;

    private Long userId;

    private String bloodGroup;

    private String emergencyContactName;

    private String emergencyContactPhone;

    private String medicalHistory;

    private String allergies;

    private String insuranceNumber;
}