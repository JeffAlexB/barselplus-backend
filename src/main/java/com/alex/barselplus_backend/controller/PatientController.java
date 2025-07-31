package com.alex.barselplus_backend.controller;

import com.alex.barselplus_backend.service.PatientService;
import com.alex.barselplus_backend.dto.PatientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api/patient")
public class PatientController {
    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping(path ="/{nationalId}")
    public ResponseEntity<?> findPatientByNationalId(@PathVariable Long nationalId) {
        //System.out.println("Fetching patient with nationalID: " + nationalId); //debug
        try {
            PatientDTO dto = patientService.findPatientByNationalID(nationalId);
            return ResponseEntity.ok(dto);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Patient not found");
        }
    }

    @PostMapping(path="/{nationalId}")
    public ResponseEntity<PatientDTO> createPatient(
            @PathVariable Long nationalId,
            @RequestBody PatientDTO dto) {
        try {
            PatientDTO create = patientService.createPatient(dto, nationalId);
            return ResponseEntity.status(HttpStatus.CREATED).body(create);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(path="/{nationalId}")
    public ResponseEntity<PatientDTO> updatePatient(
            @PathVariable Long nationalId,
            @RequestBody PatientDTO dto) {
        try {
            if (dto == null) {
                throw new IllegalArgumentException("Update data can't be null");
            }
            PatientDTO update = patientService.updatePatient(dto, nationalId);
            return ResponseEntity.ok(update);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/testing")
    public String testEndpoint() {
        return "patient controller is working?";
    }
}
