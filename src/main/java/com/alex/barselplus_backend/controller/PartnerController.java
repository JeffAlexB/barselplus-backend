package com.alex.barselplus_backend.controller;

import com.alex.barselplus_backend.dto.PartnerDTO;
import com.alex.barselplus_backend.dto.PatientDTO;
import com.alex.barselplus_backend.repository.PatientRepository;
import com.alex.barselplus_backend.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api/partner")
public class PartnerController {
    private final PartnerService partnerService;

    @Autowired
    public PartnerController(PartnerService partnerService) {
        this.partnerService = partnerService;
    }

    @GetMapping(path="/{patientID}")
    public ResponseEntity<?> getPartnerByPatientId(@PathVariable Long patientID){
        if (patientID == null || patientID <= 0) {
            return ResponseEntity.badRequest().body("Invalid patient ID");
        }
        try {
            PartnerDTO partnerDTO = partnerService.getPartnerByPatientId(patientID);
            return ResponseEntity.ok(partnerDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/testing")
    public String testEndpoint() {
        return "partner controller is working?";
    }
}
