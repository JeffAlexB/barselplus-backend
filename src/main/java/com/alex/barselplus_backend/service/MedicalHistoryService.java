package com.alex.barselplus_backend.service;

import com.alex.barselplus_backend.dto.MedicalHistoryDTO;

import com.alex.barselplus_backend.model.MedicalHistory;
import com.alex.barselplus_backend.model.Pregnancy;
import com.alex.barselplus_backend.repository.MedicalHistoryRepository;
import com.alex.barselplus_backend.repository.PregnancyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.alex.barselplus_backend.mapper.MedicalHistoryMapper.toDTO;
import static com.alex.barselplus_backend.mapper.MedicalHistoryMapper.toEntity;

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
        MedicalHistory history = medicalHistoryRepository.findByPregnancy_PregnancyID(pregnancyId)
                .orElseThrow(() -> new RuntimeException("Medical history for pregnancy ID " + pregnancyId + " not found"));

        return toDTO(history);
    }

    // straight copy of previous service layers, should this be pieced up for the layers to "fetch" from a helper class?
    public MedicalHistoryDTO createMedicalHistory(Long pregnancyId, MedicalHistoryDTO dto) {
        Pregnancy pregnancy = pregnancyRepository.findById(pregnancyId)
                .orElseThrow(() -> new IllegalArgumentException("Pregnancy not found"));

        MedicalHistory history = toEntity(dto, pregnancy);
        MedicalHistory saved = medicalHistoryRepository.save(history);
        return toDTO(saved);
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
        return toDTO(existing);
    }
}