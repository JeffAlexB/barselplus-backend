package com.alex.barselplus_backend;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Getter
@Setter
@Service
public class PatientService {
    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public PatientDTO findPatientByNationalID(long nationalID) {
        Optional<Patient> optionalPatient = patientRepository.findByNationalID(nationalID);

        if (optionalPatient.isPresent()){
            Patient patient = optionalPatient.get();
            return convertToDTO(patient);
        } else {
            throw new RuntimeException("Patient not found");
        }
    }


    private PatientDTO convertToDTO(Patient patient) {
        PatientDTO dto = new PatientDTO();
        dto.setFirstName(patient.getFirstName());
        dto.setLastName(patient.getLastName());
        dto.setDateOfBirth(patient.getDateOfBirth());
        dto.setPhoneNumber(patient.getPhoneNumber());
        dto.setOccupation(patient.getOccupation());
        return dto;
    }
}
