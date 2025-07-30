package com.alex.barselplus_backend.mapper;

import com.alex.barselplus_backend.dto.MedicalHistoryDTO;
import com.alex.barselplus_backend.model.MedicalHistory;

public class MedicalHistoryMapper {
    public static MedicalHistoryDTO toDTO(MedicalHistory history) {
        if (history == null) return null;

        MedicalHistoryDTO dto = new MedicalHistoryDTO();
        dto.setChronicDiseases(history.getChronicDiseases());
        dto.setGeneticConditions(history.getGeneticConditions());
        dto.setSmokingUse(history.getSmokingUse());
        dto.setSnusUse(history.getSnusUse());
        dto.setAlcoholUse(history.getAlcoholUse());
        dto.setOtherDrugUse(history.getOtherDrugUse());

        // Week 1 embedded values?
        if (history.getWeek1Use() != null) {
            dto.setSmokingWeek1(history.getWeek1Use().getSmoking());
            dto.setSnusWeek1(history.getWeek1Use().getSnus());
            dto.setAlcoholWeek1(history.getWeek1Use().getAlcohol());
        }

        // Week 36 embedded values?
        if (history.getWeek36Use() != null) {
            dto.setSmokingWeek36(history.getWeek36Use().getSmoking());
            dto.setSnusWeek36(history.getWeek36Use().getSnus());
            dto.setAlcoholWeek36(history.getWeek36Use().getAlcohol());
        }

        dto.setMedications(history.getMedications());
        dto.setMedList(history.getMedList());
        dto.setDrugAllergy(history.getDrugAllergy());
        dto.setFolate(history.getFolate());
        dto.setNotes(history.getNotes());

        return dto;
    }
}
