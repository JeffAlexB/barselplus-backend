package com.alex.barselplus_backend.service;

import com.alex.barselplus_backend.dto.MedicalHistoryDTO;
import com.alex.barselplus_backend.model.MedicalHistory;
import com.alex.barselplus_backend.model.Pregnancy;
import com.alex.barselplus_backend.repository.MedicalHistoryRepository;
import com.alex.barselplus_backend.repository.PregnancyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MedicalHistoryService {
    private final MedicalHistoryRepository medicalHistoryRepository;
    private final PregnancyRepository pregnancyRepository;

    @Autowired
    public MedicalHistoryService(MedicalHistoryRepository medicalHistoryRepository, PregnancyRepository pregnancyRepository) {
        this.medicalHistoryRepository = medicalHistoryRepository;
        this.pregnancyRepository = pregnancyRepository;
    }

    public MedicalHistoryDTO getMedicalHistoryByID(Long pregnancyId) {
        Optional<Pregnancy> optionalPregnancy = pregnancyRepository.findById(pregnancyId);

        if (optionalPregnancy.isPresent()) {
            Pregnancy pregnancy = optionalPregnancy.get();
            Optional<MedicalHistory> history = medicalHistoryRepository.findByPregnancy_PregnancyID(pregnancy.getPregnancyID());
            if (history.isPresent()) {
                MedicalHistory medicalHistory = history.get();
                return convertToDTO(medicalHistory);
            } else {
                throw new RuntimeException("Medical history for pregnancy ID " + pregnancyId + " not found");
            }
        } else {
            throw new RuntimeException("Pregnancy with ID " + pregnancyId + " not found");
        }
    }

    private MedicalHistoryDTO convertToDTO(MedicalHistory medicalHistory) {
        MedicalHistoryDTO dto = new MedicalHistoryDTO();
        dto.setChronicDiseases(medicalHistory.getChronicDiseases());
        dto.setGeneticConditions(medicalHistory.getGeneticConditions());
        dto.setSmokingUse(medicalHistory.getSmokingUse());
        dto.setSnusUse(medicalHistory.getSnusUse());
        dto.setAlcoholUse(medicalHistory.getAlcoholUse());
        dto.setOtherDrugUse(medicalHistory.getOtherDrugUse());

        // Week 1 embedded values?
        if (medicalHistory.getWeek1Use() != null) {
            dto.setSmokingWeek1(medicalHistory.getWeek1Use().getSmoking());
            dto.setSnusWeek1(medicalHistory.getWeek1Use().getSnus());
            dto.setAlcoholWeek1(medicalHistory.getWeek1Use().getAlcohol());
        }

        // Week 36 embedded values?
        if (medicalHistory.getWeek36Use() != null) {
            dto.setSmokingWeek36(medicalHistory.getWeek36Use().getSmoking());
            dto.setSnusWeek36(medicalHistory.getWeek36Use().getSnus());
            dto.setAlcoholWeek36(medicalHistory.getWeek36Use().getAlcohol());
        }

        dto.setMedications(medicalHistory.getMedications());
        dto.setMedList(medicalHistory.getMedList());
        dto.setDrugAllergy(medicalHistory.getDrugAllergy());
        dto.setFolate(medicalHistory.getFolate());
        dto.setNotes(medicalHistory.getNotes());

        return dto;
    }

    // straight copy of previous service layers, should this be pieced up for the layers to "fetch" from a helper class?
    public MedicalHistoryDTO createMedicalHistory(Long pregnancyId, MedicalHistoryDTO dto) {
        Pregnancy pregnancy = pregnancyRepository.findById(pregnancyId)
                .orElseThrow(() -> new IllegalArgumentException("Pregnancy not found"));

        // Map DTO to Entity
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

        // Save to DB
        MedicalHistory saved = medicalHistoryRepository.save(history);

        return convertToDTO(saved);
    }

    public MedicalHistoryDTO updateMedicalHistory(Long pregnancyId, MedicalHistoryDTO dto) {
        MedicalHistory existing = medicalHistoryRepository.findByPregnancy_PregnancyID(pregnancyId)
                .orElseThrow(() -> new IllegalArgumentException("Medical history not found for pregnancy ID: " + pregnancyId));

        existing.setChronicDiseases(dto.getChronicDiseases());
        existing.setGeneticConditions(dto.getGeneticConditions());
        existing.setSmokingUse(dto.getSmokingUse());
        existing.setSnusUse(dto.getSnusUse());
        existing.setAlcoholUse(dto.getAlcoholUse());
        existing.setOtherDrugUse(dto.getOtherDrugUse());
        existing.setMedications(dto.getMedications());
        existing.setMedList(dto.getMedList());
        existing.setDrugAllergy(dto.getDrugAllergy());
        existing.setFolate(dto.getFolate());
        existing.setNotes(dto.getNotes());

        MedicalHistory.SubstanceUseSnapshot week1 = new MedicalHistory.SubstanceUseSnapshot();
        week1.setSmoking(dto.getSmokingWeek1());
        week1.setSnus(dto.getSnusWeek1());
        week1.setAlcohol(dto.getAlcoholWeek1());
        existing.setWeek1Use(week1);

        MedicalHistory.SubstanceUseSnapshot week36 = new MedicalHistory.SubstanceUseSnapshot();
        week36.setSmoking(dto.getSmokingWeek36());
        week36.setSnus(dto.getSnusWeek36());
        week36.setAlcohol(dto.getAlcoholWeek36());
        existing.setWeek36Use(week36);

        medicalHistoryRepository.save(existing);
        return convertToDTO(existing);
    }
}