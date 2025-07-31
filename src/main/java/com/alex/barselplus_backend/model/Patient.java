package com.alex.barselplus_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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
    private Integer patientID;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "national_ID", unique = true, nullable = false)
    private Long nationalID;

    @Column(name = "address", columnDefinition = "TEXT")
    private String address;

    @Column(name = "phone_number", length = 15)
    private String phoneNumber;

    @Column(name = "marital_status", length = 50)
    private String maritalStatus;

    @Column(name = "education_level", length = 50)
    private String educationLevel;

    @Column(name = "working")
    private Boolean working;

    @Column(name = "occupation", length = 100)
    private String occupation;

    @Column(name = "work_percentage", precision = 5, scale = 2)
    private BigDecimal workPercentage;

    @Column(name = "country_of_origin", length = 100)
    private String countryOfOrigin;

    @Column(name = "language", length = 100)
    private String language;

    @Column(name = "interpreter_needed")
    private Boolean interpreterNeeded;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Pregnancy> pregnancies;
}
