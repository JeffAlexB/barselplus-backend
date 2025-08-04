package com.alex.barselplus_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicalHistoryDTO {
    private String chronicDiseases;
    private String geneticConditions;

    private String smokingUse;
    private String snusUse;
    private String alcoholUse;
    private String otherDrugUse;

    private String smokingWeek1;
    private String snusWeek1;
    private String alcoholWeek1;

    private String smokingWeek36;
    private String snusWeek36;
    private String alcoholWeek36;

    private String medications;
    private String medList;
    private Boolean drugAllergy;

    private String folate; // TODO: check form if it should be a string or boolean 'yes'/'no'
    private String notes;
}
