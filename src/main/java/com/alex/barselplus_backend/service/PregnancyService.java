package com.alex.barselplus_backend.service;

import com.alex.barselplus_backend.dto.PregnancyDTO;
import com.alex.barselplus_backend.mapper.PregnancyMapper;
import com.alex.barselplus_backend.model.Patient;
import com.alex.barselplus_backend.model.Pregnancy;
import com.alex.barselplus_backend.repository.PatientRepository;
import com.alex.barselplus_backend.repository.PregnancyRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.alex.barselplus_backend.mapper.PregnancyMapper.*;

@Service
public class PregnancyService {
    private final PatientRepository patientRepository;
    private final PregnancyRepository pregnancyRepository;

    @Autowired
    public PregnancyService(PatientRepository patientRepository, PregnancyRepository pregnancyRepository) {
        this.patientRepository = patientRepository;
        this.pregnancyRepository = pregnancyRepository;
    }

    public PregnancyDTO getPregnancyById(Long pregnancyId) {
        Optional<Pregnancy> optionalPregnancy = pregnancyRepository.findById(pregnancyId);

        if (optionalPregnancy.isPresent()) {
            Pregnancy pregnancy = optionalPregnancy.get();
            return toDTO(pregnancy);
        } else {
            throw new RuntimeException("Pregnancy with ID " + pregnancyId + " not found");
        }
    }

    // down-the-road functionality
    public List<PregnancyDTO> getAllPregnanciesByPatient(Long patientId) {
        List<Pregnancy> pregnancies = pregnancyRepository.findAllByPatient_PatientID(patientId);

        return pregnancies.stream()
                .map(PregnancyMapper::toDTO)
                .toList();
    }

    public PregnancyDTO createPregnancy(Long patientId, PregnancyDTO dto){
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(()-> new IllegalArgumentException("Patient not found"));

        Pregnancy pregnancy = toEntity(dto, patient);
        Pregnancy savedPregnancy = pregnancyRepository.save(pregnancy);
        return toDTO(savedPregnancy);
    }

    public PregnancyDTO updatePregnancy(Long pregnancyId, PregnancyDTO dto) {
        Pregnancy pregnancy = pregnancyRepository.findById(pregnancyId)
                .orElseThrow(() -> new EntityNotFoundException("pregnancy not found"));
        updateDTO(dto, pregnancy);
        pregnancyRepository.save(pregnancy);
        return PregnancyMapper.toDTO(pregnancy);
    }
}
