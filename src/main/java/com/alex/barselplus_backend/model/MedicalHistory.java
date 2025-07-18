package com.alex.barselplus_backend.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "medical_history")
public class MedicalHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    private Long historyID;

    @OneToOne
    @JoinColumn(name = "pregnancy_id", nullable = false, unique = true)
    private Pregnancy pregnancy;

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

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "smoking", column = @Column(name = "smoking_week1")),
            @AttributeOverride(name = "snus", column = @Column(name = "snus_week1")),
            @AttributeOverride(name = "alcohol", column = @Column(name = "alcohol_week1"))
    })
    private SubstanceUseSnapshot week1Use;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "smoking", column = @Column(name = "smoking_week36")),
            @AttributeOverride(name = "snus", column = @Column(name = "snus_week36")),
            @AttributeOverride(name = "alcohol", column = @Column(name = "alcohol_week36"))
    })
    private SubstanceUseSnapshot week36Use;

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

    @Embeddable
    @Data
    public static class SubstanceUseSnapshot {
        private String smoking;
        private String snus;
        private String alcohol;
    }
}
