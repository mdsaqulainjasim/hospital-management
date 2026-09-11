package com.hospital.management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(RoleNotFoundException.class)
        public ResponseEntity<String> handleRoleNotFoundException(RoleNotFoundException exception) {
                return ResponseEntity
                                .status(HttpStatus.NOT_FOUND)
                                .body(exception.getMessage());
        }

        @ExceptionHandler(UserNotFoundException.class)
        public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException exception) {

                return ResponseEntity
                                .status(HttpStatus.NOT_FOUND)
                                .body(exception.getMessage());
        }

        @ExceptionHandler(DepartmentNotFoundException.class)
        public ResponseEntity<String> handleDepartmentNotFoundException(
                        DepartmentNotFoundException exception) {

                return ResponseEntity
                                .status(HttpStatus.NOT_FOUND)
                                .body(exception.getMessage());
        }

        @ExceptionHandler(DoctorNotFoundException.class)
        public ResponseEntity<String> handleDoctorNotFoundException(
                        DoctorNotFoundException exception) {

                return ResponseEntity
                                .status(HttpStatus.NOT_FOUND)
                                .body(exception.getMessage());
        }

        @ExceptionHandler(PatientNotFoundException.class)
        public ResponseEntity<String> handlePatientNotFoundException(
                        PatientNotFoundException exception) {

                return ResponseEntity
                                .status(HttpStatus.NOT_FOUND)
                                .body(exception.getMessage());
        }

        @ExceptionHandler(StaffNotFoundException.class)
        public ResponseEntity<String> handleStaffNotFoundException(
                        StaffNotFoundException exception) {

                return ResponseEntity
                                .status(HttpStatus.NOT_FOUND)
                                .body(exception.getMessage());
        }

        @ExceptionHandler(DoctorAvailabilityNotFoundException.class)
        public ResponseEntity<String> handleDoctorAvailabilityNotFoundException(
                        DoctorAvailabilityNotFoundException exception) {

                return ResponseEntity
                                .status(HttpStatus.NOT_FOUND)
                                .body(exception.getMessage());
        }

        @ExceptionHandler(AppointmentNotFoundException.class)
        public ResponseEntity<String> handleAppointmentNotFoundException(
                        AppointmentNotFoundException exception) {

                return ResponseEntity
                                .status(HttpStatus.NOT_FOUND)
                                .body(exception.getMessage());
        }
}