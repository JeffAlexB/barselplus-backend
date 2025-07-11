package com.alex.barselplus_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id", nullable = false)
    private Long patientID;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "national_ID", unique = true, nullable = false)
    private Long nationalID;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "marital_status")
    private String maritalStatus;

    @Column(name = "education_level")
    private String educationLevel;

    @Column(name = "working")
    private Boolean working;

    @Column(name = "occupation")
    private String occupation;

    @Column(name = "work_percentage")
    private float workPercentage;

    @Column(name = "country_of_origin")
    private String countryOfOrigin;

    @Column(name = "language")
    private String language;

    @Column(name = "interpreter_needed")
    private Boolean interpreterNeeded;
}
