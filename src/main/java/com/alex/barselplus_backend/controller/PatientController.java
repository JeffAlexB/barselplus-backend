package com.alex.barselplus_backend.controller;

import com.alex.barselplus_backend.service.PatientService;
import com.alex.barselplus_backend.dto.PatientDTO;
import org.springframework.beans.factory.annotation.Autowired;
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
    public PatientDTO findPatientByNationalId(@PathVariable Long nationalId) {
        //System.out.println("Fetching patient with nationalID: " + nationalId); //debug
        return patientService.findPatientByNationalID(nationalId);
    }

    @GetMapping("/testing")
    public String testEndpoint() {
        return "patient controller is working?";
    }
}
