package com.alex.barselplus_backend.controller;

import com.alex.barselplus_backend.dto.PartnerDTO;
import com.alex.barselplus_backend.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api/partner")
public class PartnerController {
    private final PartnerService partnerService;

    @Autowired
    public PartnerController(PartnerService partnerService) {
        this.partnerService = partnerService;
    }

    // GET by patient ID
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

    // POST for new partner
    @PostMapping(path = "/{patientID}")
    public ResponseEntity<?> createPartnerForPatient(
            @PathVariable Long patientID,
            @RequestBody PartnerDTO partnerDTO
    ){
        try {
            PartnerDTO create = partnerService.createPartner(patientID, partnerDTO);
            return ResponseEntity.ok(create);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping("/testing")
    public String testEndpoint() {
        return "partner controller is working?";
    }
}
