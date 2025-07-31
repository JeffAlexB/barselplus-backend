package com.alex.barselplus_backend.mapper;

import com.alex.barselplus_backend.dto.PartnerDTO;
import com.alex.barselplus_backend.model.Partner;

public class PartnerMapper {
    public static PartnerDTO toDTO(Partner partner) {
        PartnerDTO dto = new PartnerDTO();
        dto.setFirstName(partner.getFirst_name());
        dto.setLastName(partner.getLast_name());
        dto.setDateOfBirth(partner.getDate_of_birth());
        dto.setPhoneNumber(partner.getPhoneNumber());
        dto.setOccupation(partner.getOccupation());
        return dto;
    }

    public static Partner toEntity(PartnerDTO dto) {
        Partner partner = new Partner();
        partner.setFirst_name(dto.getFirstName());
        partner.setLast_name(dto.getLastName());
        partner.setDate_of_birth(dto.getDateOfBirth());
        partner.setPhoneNumber(dto.getPhoneNumber());
        partner.setOccupation(dto.getOccupation());
        return partner;
    }

    public static void updateDTO(Partner partner, PartnerDTO dto) {
        partner.setFirst_name(dto.getFirstName());
        partner.setLast_name(dto.getLastName());
        partner.setDate_of_birth(dto.getDateOfBirth());
        partner.setPhoneNumber(dto.getPhoneNumber());
        partner.setOccupation(dto.getOccupation());
    }
}
