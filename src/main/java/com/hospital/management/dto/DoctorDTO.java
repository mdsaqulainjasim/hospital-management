package com.hospital.management.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DoctorDTO {

    private Long id;

    private Long userId;

    private Long departmentId;

    private String specialization;

    private String licenseNumber;

    private Integer experienceYears;

    private String qualification;

    private BigDecimal consultationFee;

    private String status;
}