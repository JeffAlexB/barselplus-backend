package com.alex.barselplus_backend.service;

import com.alex.barselplus_backend.dto.PartnerDTO;
import com.alex.barselplus_backend.mapper.PartnerMapper;
import com.alex.barselplus_backend.model.Partner;
import com.alex.barselplus_backend.model.Patient;
import com.alex.barselplus_backend.repository.PartnerRepository;
import com.alex.barselplus_backend.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.alex.barselplus_backend.mapper.PartnerMapper.*;

@Service
public class PartnerService {
    private final PartnerRepository partnerRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public PartnerService(PartnerRepository partnerRepository, PatientRepository patientRepository) {
        this.partnerRepository = partnerRepository;
        this.patientRepository = patientRepository;
    }

    public PartnerDTO getPartnerByPatientId(long patientId) {
        Optional<Partner> optionalPartner = partnerRepository.findByPatient_PatientID(patientId);

        if (optionalPartner.isPresent()){
            Partner partner = optionalPartner.get();
            return toDTO(partner);
        } else {
            throw new RuntimeException("Partner not found");
        }
    }

    public PartnerDTO createPartner(Long patientId, PartnerDTO dto){
        Optional <Partner> optionalPartner = partnerRepository.findByPatient_PatientID(patientId);
        if (optionalPartner.isPresent()){
            throw new IllegalArgumentException("Partner already exists");
        }
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found"));

        Partner entity = toEntity(dto);
        entity.setPatient(patient);
        Partner savedPartner = partnerRepository.save(entity);
        return toDTO(savedPartner);
    }

    public PartnerDTO updatePartner(Long patientId, PartnerDTO dto){
        Partner partner = partnerRepository.findByPatient_PatientID(patientId)
                .orElseThrow(() -> new EntityNotFoundException("Partner not found for patient: " + patientId));

        updateDTO(partner, dto);
        partnerRepository.save(partner);
        return PartnerMapper.toDTO(partner);
    }
}
