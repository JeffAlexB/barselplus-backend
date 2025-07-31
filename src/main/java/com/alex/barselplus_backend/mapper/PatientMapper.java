package com.alex.barselplus_backend.mapper;

import com.alex.barselplus_backend.dto.PatientDTO;
import com.alex.barselplus_backend.model.Patient;

public class PatientMapper {
    public static PatientDTO toDTO(Patient patient) {
        if (patient == null) return null;

        PatientDTO dto = new PatientDTO();
        dto.setFirstName(patient.getFirstName());
        dto.setLastName(patient.getLastName());
        dto.setDateOfBirth(patient.getDateOfBirth());
        dto.setPhoneNumber(patient.getPhoneNumber());
        dto.setOccupation(patient.getOccupation());

        return dto;
    }

    public static Patient toEntity(PatientDTO dto, Patient patient) {
        patient.setFirstName(dto.getFirstName());
        patient.setLastName(dto.getLastName());
        patient.setDateOfBirth(dto.getDateOfBirth());
        patient.setPhoneNumber(dto.getPhoneNumber());
        patient.setOccupation(dto.getOccupation());

        return patient;
    }

    public static void updateFromDTO(Patient patient, PatientDTO dto) {
        patient.setFirstName(dto.getFirstName());
        patient.setLastName(dto.getLastName());
        patient.setDateOfBirth(dto.getDateOfBirth());
        patient.setPhoneNumber(dto.getPhoneNumber());
        patient.setOccupation(dto.getOccupation());
    }
}
