package com.alex.barselplus_backend.service;

import com.alex.barselplus_backend.dto.PartnerDTO;
import com.alex.barselplus_backend.model.Partner;
import com.alex.barselplus_backend.model.Patient;
import com.alex.barselplus_backend.repository.PartnerRepository;
import com.alex.barselplus_backend.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PartnerService {
    private final PatientRepository patientRepository;
    private final PartnerRepository partnerRepository;

    @Autowired
    public PartnerService(PatientRepository patientRepository, PartnerRepository partnerRepository) {
        this.patientRepository = patientRepository;
        this.partnerRepository = partnerRepository;
    }

    public PartnerDTO getPartnerByPatientId(long patientId) {
        Optional<Partner> optionalPartner = partnerRepository.findByPatient_PatientID(patientId);

        if (optionalPartner.isPresent()){
            Partner partner = optionalPartner.get();
            return convertToDTO(partner);
        } else {
            throw new RuntimeException("Partner not found");
        }
    }

    private PartnerDTO convertToDTO(Partner partner) {
        PartnerDTO dto = new PartnerDTO();
        dto.setFirstName(partner.getFirst_name());
        dto.setLastName(partner.getLast_name());
        dto.setDateOfBirth(partner.getDate_of_birth());
        dto.setPhoneNumber(partner.getPhoneNumber());
        dto.setOccupation(partner.getOccupation());
        return dto;
    }

    public PartnerDTO createPartner(Long patientId, PartnerDTO dto){
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(()-> new IllegalArgumentException("Patient not found"));

        // DTO conversion
        Partner partner = new Partner();
        partner.setPatient(patient);
        partner.setFirst_name(dto.getFirstName());
        partner.setLast_name(dto.getLastName());
        partner.setDate_of_birth(dto.getDateOfBirth());
        partner.setPhoneNumber(dto.getPhoneNumber());
        partner.setOccupation(dto.getOccupation());

        Partner savedPartner = partnerRepository.save(partner);
        return convertToDTO(savedPartner);
    }
}
