package com.hospital.management.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StaffDTO {

    private Long id;

    private Long userId;

    private String designation;

    private Long departmentId;

    private LocalDate joiningDate;

    private BigDecimal salary;

    private String status;
}