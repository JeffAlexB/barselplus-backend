package com.alex.barselplus_backend.repository;

import com.alex.barselplus_backend.model.Pregnancy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PregnancyRepository extends JpaRepository<Pregnancy, Long> {
    Optional<Pregnancy> findByPatient_PatientID(long patientID);
    List<Pregnancy> findAllByPatient_PatientID(long patientID);
}
