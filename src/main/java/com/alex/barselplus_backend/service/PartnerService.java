package com.alex.barselplus_backend.service;

import com.alex.barselplus_backend.dto.PartnerDTO;
import com.alex.barselplus_backend.model.Partner;
import com.alex.barselplus_backend.repository.PartnerRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Getter
@Setter
@Service
public class PartnerService {
    private final PartnerRepository partnerRepository;

    @Autowired
    public PartnerService(PartnerRepository partnerRepository) {
        this.partnerRepository = partnerRepository;
    }

    public PartnerDTO findPartnerByNationalID(long nationalID) {
        Optional<Partner> optionalPartner = partnerRepository.findByNationalID(nationalID);

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
}
