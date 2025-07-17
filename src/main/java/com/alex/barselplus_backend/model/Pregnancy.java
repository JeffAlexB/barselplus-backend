package com.alex.barselplus_backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pregnancy")
public class Pregnancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pregnancy_id")
    private Long pregnancyID;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Column(name = "last_mens", nullable = false)
    private LocalDate lastMens;

    @Column(name = "estimated_duedate", nullable = false)
    private LocalDate dueDate;

    @Column(name = "ultrasound_duedate")
    private LocalDate ulDueDate;

    @Column(name = "corrected_duedate")
    private LocalDate correctedDueDate;

    @Column(name = "fetal_diagnostic")
    private Boolean fetusDiagnostics;

    @Column(name = "assisted_conception")
    private Boolean assistedConcept;

    @Column(name = "assist_concept_date")
    private LocalDate assistDate;

    @Column(name = "multiple_fetus")
    private Boolean multipleFetus;

    @Column(name = "prepregnant_height")
    private Integer prePregnancyHeight;

    @Column(name = "prepregnant_weight")
    private Integer prePregnancyWeight;

    @Column(name = "prepregnant_bmi")
    private Double prePregnancyBMI;

    @Column(name = "breastfeed_consult")
    private Boolean breastfeedGuide;

    @Column(name = "prenatal_consult")
    private Boolean prenatalConsult;

    @Column(name = "coparent_declaration")
    private Boolean coparentDeclaration;

    @Column(name = "maternity_ward")
    private String maternityWard;

    @Column(name = "ward_phone")
    private String wardTlf;

    @Column(name = "healthstation")
    private String healthstation;

    @Column(name = "hs_address")
    private String hsAdresse;

    @Column(name = "hs_phone")
    private String hsTelefon;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;
}

