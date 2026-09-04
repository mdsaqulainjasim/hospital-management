package com.hospital.management.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @Column(nullable = false, length = 100)
    private String specialization;

    @Column(name = "license_number", nullable = false, unique = true, length = 100)
    private String licenseNumber;

    @Column(name = "experience_years")
    private Integer experienceYears;

    @Column(length = 255)
    private String qualification;

    @Column(name = "consultation_fee", precision = 10, scale = 2)
    private java.math.BigDecimal consultationFee;

    @Column(nullable = false, length = 20)
    private String status = "ACTIVE";

}
