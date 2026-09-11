package com.hospital.management.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String name;
    private String email;

    @JsonProperty (access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private String phone;
    private String gender;
    private LocalDate dateOfBirth;
    private String address;
    private Long roleId;
    private String status;
}