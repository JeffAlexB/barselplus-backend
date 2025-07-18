package com.alex.barselplus_backend.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@ToString(exclude = "patient")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "medical_history")
public class MedicalHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    private Long historyID;

    @ManyToOne(optional = false)
    @JoinColumn(
            name = "patient_ID",
            referencedColumnName = "patient_ID",
            nullable = false,
            foreignKey = @ForeignKey(
                    name = "fk_medicalhistory_patient",
                    foreignKeyDefinition = "FOREIGN KEY (patient_ID) REFERENCES patient(patient_ID) ON DELETE CASCADE"
            )
    )
    private Patient patient;

    @Column(name = "chronic_diseases")
    private String chronicDiseases;

    @Column(name = "genetic_conditions")
    private String geneticConditions;

    @Column(name = "smoking_use")
    private String smokingUse;

    @Column(name = "snus_use")
    private String snusUse;

    @Column(name = "alcohol_use")
    private String alcoholUse;

    @Column(name = "other_druguse")
    private String otherDrugUse;

    @Column(name = "smoking_week1")
    private String smokingWeek1;

    @Column(name = "snus_week1")
    private String snusWeek1;

    @Column(name = "alcohol_week1")
    private String alcoholWeek1;

    @Column(name = "smoking_week36")
    private String smokingWeek36;

    @Column(name = "snus_week36")
    private String snusWeek36;

    @Column(name = "alcohol_week36")
    private String alcoholWeek36;

    @Column(name = "medications")
    private String medications;

    @Column(name = "med_list")
    private String medList;

    @Column(name = "drug_allergy")
    private Boolean drugAllergy;

    @Column(name = "folate")
    private String folate;

    @Column(name = "notes")
    private String notes;

}
