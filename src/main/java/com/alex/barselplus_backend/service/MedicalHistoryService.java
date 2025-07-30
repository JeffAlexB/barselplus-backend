package com.alex.barselplus_backend.service;

import com.alex.barselplus_backend.dto.MedicalHistoryDTO;

import com.alex.barselplus_backend.model.MedicalHistory;
import com.alex.barselplus_backend.model.Pregnancy;
import com.alex.barselplus_backend.repository.MedicalHistoryRepository;
import com.alex.barselplus_backend.repository.PregnancyRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.alex.barselplus_backend.mapper.MedicalHistoryMapper.*;

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
                .orElseThrow(() -> new EntityNotFoundException("Medical history for pregnancy ID " + pregnancyId + " not found"));

        return toDTO(history);
    }

    // straight copy of previous service layers, should this be pieced up for the layers to "fetch" from a helper class?
    public MedicalHistoryDTO createMedicalHistory(Long pregnancyId, MedicalHistoryDTO dto) {
        Pregnancy pregnancy = pregnancyRepository.findById(pregnancyId)
                .orElseThrow(() -> new EntityNotFoundException("Pregnancy not found"));

        MedicalHistory history = toEntity(dto, pregnancy);
        MedicalHistory saved = medicalHistoryRepository.save(history);
        return toDTO(saved);
    }

    public MedicalHistoryDTO updateMedicalHistory(Long pregnancyId, MedicalHistoryDTO dto) {
        MedicalHistory existing = medicalHistoryRepository.findByPregnancy_PregnancyID(pregnancyId)
                .orElseThrow(() -> new EntityNotFoundException("Medical history not found for pregnancy ID: " + pregnancyId));

        updateFromDTO(existing, dto);
        medicalHistoryRepository.save(existing);

        return toDTO(existing);
    }
}