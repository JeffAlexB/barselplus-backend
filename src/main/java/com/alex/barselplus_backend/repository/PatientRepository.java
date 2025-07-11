package com.alex.barselplus_backend.repository;

import com.alex.barselplus_backend.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByNationalID(long nationalID);
    // consider adding different methods for fetching, like number, email, JWT token(if possible?)
    // if BankID is available in the future, map the BankID token to NationalID --> then query DB for pert. data
}

