package com.alex.barselplus_backend.controller;

import com.alex.barselplus_backend.dto.MedicalHistoryDTO;
import com.alex.barselplus_backend.service.MedicalHistoryService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/pregnancies")
public class MedicalHistoryController {
    private final MedicalHistoryService medicalHistoryService;

    @Autowired
    public MedicalHistoryController(MedicalHistoryService medicalHistoryService) {
        this.medicalHistoryService = medicalHistoryService;
    }

    @GetMapping(path="/{pregnancyId}/medical-history")
    public ResponseEntity<MedicalHistoryDTO> getMedicalHistory(@PathVariable Long pregnancyId) {
        if (pregnancyId == null || pregnancyId <= 0) {
            return ResponseEntity.badRequest().body(null);
        }
        try {
            MedicalHistoryDTO saved = medicalHistoryService.getMedicalHistoryByID(pregnancyId);
            return ResponseEntity.ok(saved);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping(path="/{pregnancyId}/medical-history")
    public ResponseEntity<MedicalHistoryDTO> createMedicalHistory(
            @PathVariable Long pregnancyId,
            @RequestBody MedicalHistoryDTO dto) {
        try {
            MedicalHistoryDTO create = medicalHistoryService.createMedicalHistory(pregnancyId, dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(create);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(path="/{pregnancyId}/medical-history")
    public ResponseEntity<MedicalHistoryDTO> updateMedicalHistory(
            @PathVariable Long pregnancyId,
            @RequestBody MedicalHistoryDTO dto) {
        try {
            MedicalHistoryDTO updated = medicalHistoryService.updateMedicalHistory(pregnancyId, dto);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path="/testing")
    public String testEndpoint() {
        return "medical history controller is working?";
    }
}

