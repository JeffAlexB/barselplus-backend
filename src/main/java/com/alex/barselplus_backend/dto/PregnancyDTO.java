package com.alex.barselplus_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PregnancyDTO {
    private LocalDate lastMens;
    private LocalDate dueDate;
    private Boolean multipleFetus;
    private Integer prePregnancyHeight;
    private Integer prePregnancyWeight;
    private String maternityWard;
}
