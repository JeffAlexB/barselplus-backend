package com.alex.barselplus_backend.mapper;

import com.alex.barselplus_backend.dto.PregnancyDTO;
import com.alex.barselplus_backend.model.Patient;
import com.alex.barselplus_backend.model.Pregnancy;

public class PregnancyMapper {
    public static PregnancyDTO toDTO(Pregnancy pregnancy) {
        PregnancyDTO dto = new PregnancyDTO();
        dto.setLastMens(pregnancy.getLastMens());
        dto.setDueDate(pregnancy.getDueDate());
        dto.setMultipleFetus(pregnancy.getMultipleFetus());
        dto.setPrePregnancyHeight(pregnancy.getPrePregnancyHeight());
        dto.setPrePregnancyWeight(pregnancy.getPrePregnancyWeight());
        dto.setMaternityWard(pregnancy.getMaternityWard());
        return dto;
    }

    public static Pregnancy toEntity(PregnancyDTO dto, Patient patient) {
        Pregnancy pregnancy = new Pregnancy();
        pregnancy.setLastMens(dto.getLastMens());
        pregnancy.setDueDate(dto.getDueDate());
        pregnancy.setMultipleFetus(dto.getMultipleFetus());
        pregnancy.setPrePregnancyHeight(dto.getPrePregnancyHeight());
        pregnancy.setPrePregnancyWeight(dto.getPrePregnancyWeight());
        pregnancy.setMaternityWard(dto.getMaternityWard());
        pregnancy.setPatient(patient);
        return pregnancy;
    }

    public static void updateDTO(PregnancyDTO dto, Pregnancy pregnancy) {
        pregnancy.setLastMens(dto.getLastMens());
        pregnancy.setDueDate(dto.getDueDate());
        pregnancy.setMultipleFetus(dto.getMultipleFetus());
        pregnancy.setPrePregnancyHeight(dto.getPrePregnancyHeight());
        pregnancy.setPrePregnancyWeight(dto.getPrePregnancyWeight());
        pregnancy.setMaternityWard(dto.getMaternityWard());
    }
}
