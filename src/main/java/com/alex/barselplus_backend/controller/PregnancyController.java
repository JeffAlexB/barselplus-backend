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

    // unsure if this will work at length - duck-taped together(?)
    @GetMapping(path="/patient/{patientId}/pregnancies")
    public ResponseEntity<List<PregnancyDTO>> getAllPregnancies(@PathVariable Long patientId) {
        List<PregnancyDTO> pregnancies = pregnancyService.getAllPregnanciesByPatient(patientId);

        if (pregnancies.isEmpty()) {
            return ResponseEntity.noContent().build(); // HTTP 204
        }
        return ResponseEntity.ok(pregnancies); // HTTP 200
    }

    @PostMapping(path="/patient/{patientId}")
    public ResponseEntity<?> createPregnancy(@PathVariable Long patientId, @RequestBody @Valid PregnancyDTO dto) {
        try {
            PregnancyDTO saved = pregnancyService.createPregnancy(patientId, dto);
            return ResponseEntity.ok(saved);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(path="/{pregnancyId}")
    public ResponseEntity<PregnancyDTO> updatePregnancy(
            @PathVariable Long pregnancyId,
            @RequestBody @Valid PregnancyDTO dto) {
        try {
            if (dto == null) {
                throw new IllegalArgumentException("Update data can't be null");
            }
            PregnancyDTO update = pregnancyService.updatePregnancy(pregnancyId, dto);
            return ResponseEntity.ok(update);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(path="/testing")
    public String testEndpoint() {
        return "pregnancy controller is working?";
    }
}
