package com.alex.barselplus_backend;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> getByNationalID(long nationalID);
    // consider adding different methods for fetching, like number, email, JWT token(if possible?)
    // if BankID is available in the future, map the BankID token to NationalID --> then query DB for pert. data
}

