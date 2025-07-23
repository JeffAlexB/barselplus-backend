package com.alex.barselplus_backend.controller;

import com.alex.barselplus_backend.dto.MedicalHistoryDTO;
import com.alex.barselplus_backend.repository.MedicalHistoryRepository;
import com.alex.barselplus_backend.service.MedicalHistoryService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/pregnancies")
public class MedicalHistoryController {
    private final MedicalHistoryRepository medicalHistoryRepository;
    private final MedicalHistoryService medicalHistoryService;

    @Autowired
    public MedicalHistoryController(MedicalHistoryRepository medicalHistoryRepository, MedicalHistoryService medicalHistoryService) {
        this.medicalHistoryRepository = medicalHistoryRepository;
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

    @GetMapping(path="/testing")
    public String testEndpoint() {
        return "medical history controller is working?";
    }
}
