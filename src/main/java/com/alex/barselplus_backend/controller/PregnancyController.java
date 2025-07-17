package com.alex.barselplus_backend.controller;

import com.alex.barselplus_backend.dto.PregnancyDTO;
import com.alex.barselplus_backend.service.PregnancyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/pregnancy")
public class PregnancyController {
    private final PregnancyService pregnancyService;

    @Autowired
    public PregnancyController(PregnancyService pregnancyService) {
        this.pregnancyService = pregnancyService;
    }

    // GET by patient ID
    @GetMapping(path="/{pregnancyId}")
    public ResponseEntity<?> getPregnancyById(@PathVariable Long pregnancyId) {
        if (pregnancyId == null || pregnancyId <= 0) {
            return ResponseEntity.badRequest().body("Invalid pregnancy ID");
        }
        try {
            PregnancyDTO dto = pregnancyService.getPregnancyById(pregnancyId); // ok
            return ResponseEntity.ok(dto);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/patient/{patientId}")
    public ResponseEntity<?> createPregnancy(@PathVariable Long patientId, @RequestBody @Valid PregnancyDTO dto) {
        try {
            PregnancyDTO saved = pregnancyService.createPregnancy(patientId, dto);
            return ResponseEntity.ok(saved);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/patient/{patientId}/pregnancies")
    public List<PregnancyDTO> getAllPregnancies(@PathVariable Long patientId) {
        return pregnancyService.getAllPregnanciesByPatient(patientId);
    }

    @GetMapping("/testing")
    public String testEndpoint() {
        return "pregnancy controller is working?";
    }
}
