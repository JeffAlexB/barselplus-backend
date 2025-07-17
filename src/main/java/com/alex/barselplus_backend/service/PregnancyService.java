package com.alex.barselplus_backend.service;

import com.alex.barselplus_backend.dto.PartnerDTO;
import com.alex.barselplus_backend.dto.PregnancyDTO;
import com.alex.barselplus_backend.model.Partner;
import com.alex.barselplus_backend.model.Patient;
import com.alex.barselplus_backend.model.Pregnancy;
import com.alex.barselplus_backend.repository.PatientRepository;
import com.alex.barselplus_backend.repository.PregnancyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
            return convertToDTO(pregnancy);
        } else {
            throw new RuntimeException("Pregnancy with ID " + pregnancyId + " not found");
        }
    }

    // down-the-road functionality
    public List<PregnancyDTO> getAllPregnanciesByPatient(Long patientId) {
        return pregnancyRepository.findAllByPatient_PatientID(patientId)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }


    private PregnancyDTO convertToDTO(Pregnancy pregnancy) {
        PregnancyDTO dto = new PregnancyDTO();
        dto.setLastMens(pregnancy.getLastMens());
        dto.setDueDate(pregnancy.getDueDate());
        dto.setMultipleFetus(pregnancy.getMultipleFetus());
        dto.setPrePregnancyHeight(pregnancy.getPrePregnancyHeight());
        dto.setPrePregnancyWeight(pregnancy.getPrePregnancyWeight());
        dto.setMaternityWard(pregnancy.getMaternityWard());
        return dto;
    }

    public PregnancyDTO createPregnancy(Long patientId, PregnancyDTO dto){
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(()-> new IllegalArgumentException("Patient not found"));

        // DTO conversion
        Pregnancy pregnancy = new Pregnancy();
        pregnancy.setPatient(patient);
        pregnancy.setLastMens(dto.getLastMens());
        pregnancy.setDueDate(dto.getDueDate());
        pregnancy.setMultipleFetus(dto.getMultipleFetus());
        pregnancy.setPrePregnancyHeight(dto.getPrePregnancyHeight());
        pregnancy.setPrePregnancyWeight(dto.getPrePregnancyWeight());
        pregnancy.setMaternityWard(dto.getMaternityWard());

        Pregnancy savedPregnancy = pregnancyRepository.save(pregnancy);
        return convertToDTO(savedPregnancy);
    }
}
