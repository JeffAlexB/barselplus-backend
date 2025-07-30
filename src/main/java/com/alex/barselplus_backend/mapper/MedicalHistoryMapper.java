package com.alex.barselplus_backend.mapper;

import com.alex.barselplus_backend.dto.MedicalHistoryDTO;
import com.alex.barselplus_backend.model.MedicalHistory;
import com.alex.barselplus_backend.model.Pregnancy;

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

    public static MedicalHistory toEntity(MedicalHistoryDTO dto, Pregnancy pregnancy) {
        MedicalHistory history = new MedicalHistory();
        history.setPregnancy(pregnancy);
        history.setChronicDiseases(dto.getChronicDiseases());
        history.setGeneticConditions(dto.getGeneticConditions());
        history.setSmokingUse(dto.getSmokingUse());
        history.setSnusUse(dto.getSnusUse());
        history.setAlcoholUse(dto.getAlcoholUse());
        history.setOtherDrugUse(dto.getOtherDrugUse());

        MedicalHistory.SubstanceUseSnapshot week1 = new MedicalHistory.SubstanceUseSnapshot();
        week1.setSmoking(dto.getSmokingWeek1());
        week1.setSnus(dto.getSnusWeek1());
        week1.setAlcohol(dto.getAlcoholWeek1());
        history.setWeek1Use(week1);

        MedicalHistory.SubstanceUseSnapshot week36 = new MedicalHistory.SubstanceUseSnapshot();
        week36.setSmoking(dto.getSmokingWeek36());
        week36.setSnus(dto.getSnusWeek36());
        week36.setAlcohol(dto.getAlcoholWeek36());
        history.setWeek36Use(week36);

        history.setMedications(dto.getMedications());
        history.setMedList(dto.getMedList());
        history.setDrugAllergy(dto.getDrugAllergy());
        history.setFolate(dto.getFolate());
        history.setNotes(dto.getNotes());

        return history;
    }

}
