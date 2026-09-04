package com.hospital.management.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(
    name = "beds",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"room_id", "bed_number"})
    }
)
public class Bed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @Column(name = "bed_number", nullable = false, length = 20)
    private String bedNumber;

    @Column(name = "bed_type", nullable = false, length = 50)
    private String bedType;

    @Column(nullable = false, length = 20)
    private String status = "AVAILABLE";
}