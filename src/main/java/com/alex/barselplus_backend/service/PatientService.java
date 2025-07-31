package com.alex.barselplus_backend.service;

import com.alex.barselplus_backend.dto.PatientDTO;
import com.alex.barselplus_backend.mapper.PatientMapper;
import com.alex.barselplus_backend.model.Patient;
import com.alex.barselplus_backend.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.alex.barselplus_backend.mapper.PatientMapper.*;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public PatientDTO findPatientByNationalID(long nationalId) {
        Optional<Patient> optionalPatient = patientRepository.findByNationalID(nationalId);

        if (optionalPatient.isPresent()){
            Patient patient = optionalPatient.get();
            return toDTO(patient);
        } else {
            throw new RuntimeException("Patient not found");
        }
    }

   public PatientDTO createPatient(PatientDTO dto, Long nationalId) {
        Optional<Patient> patient = patientRepository.findByNationalID(nationalId);
        if (patient.isPresent()) {
            throw new IllegalArgumentException("Patient already exists");
        }
        Patient newPatient = new Patient();
        newPatient.setNationalID(nationalId);
        Patient entity = toEntity(dto, newPatient);
        Patient savedPatient = patientRepository.save(entity);
        return toDTO(savedPatient);
    }

    public PatientDTO updatePatient(PatientDTO dto, Long nationalId) {
        Patient patient = patientRepository.findByNationalID(nationalId)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found: " + nationalId));

        updateFromDTO(patient, dto);
        patientRepository.save(patient);
        return PatientMapper.toDTO(patient);
    }
}
