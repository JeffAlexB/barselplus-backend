package com.alex.barselplus_backend.controller;

import com.alex.barselplus_backend.repository.MedicalHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/medicalhistory")
public class MedicalHistoryController {
    private final MedicalHistoryRepository medicalHistoryRepository;

    @Autowired
    public MedicalHistoryController(MedicalHistoryRepository medicalHistoryRepository) {
        this.medicalHistoryRepository = medicalHistoryRepository;
    }

    @GetMapping("/testing")
    public String testEndpoint() {
        return "medical history controller is working?";
    }
}
