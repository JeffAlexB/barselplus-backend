package com.alex.barselplus_backend.views;

import com.alex.barselplus_backend.dto.PatientDTO;
import com.alex.barselplus_backend.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path="/patients")
public class PatientViewController {
    private final PatientService patientService;

    @Autowired
    public PatientViewController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public String getPatientDashboard(Model model) {
        // Hardcoded ID for testing
        PatientDTO patient = patientService.findPatientByNationalID(123456789);
        System.out.println("Patient loaded: " + patient.getFirstName());

        model.addAttribute("patient", patient);
        return "patient/list";
    }

    @GetMapping("/test")
    public String testLayout() {
        return "patient/test";
    }
}
