package com.alex.barselplus_backend.repository;

import com.alex.barselplus_backend.model.MedicalHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicalHistoryRepository extends JpaRepository<MedicalHistory, Long> {
    Optional<MedicalHistory> findByPregnancy_PregnancyID(Long pregnancyId);
}
