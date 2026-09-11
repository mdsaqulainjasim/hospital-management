package com.hospital.management.exception;

public class DoctorAvailabilityNotFoundException extends RuntimeException {

    public DoctorAvailabilityNotFoundException(String message) {
        super(message);
    }
}