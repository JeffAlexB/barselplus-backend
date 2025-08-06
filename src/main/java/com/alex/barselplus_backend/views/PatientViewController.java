package com.alex.barselplus_backend.views;

import com.alex.barselplus_backend.dto.PatientDTO;
import com.alex.barselplus_backend.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/patients")
public class PatientViewController {
    private final PatientService patientService;

    @Autowired
    public PatientViewController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public String getPatientDashboard(Model model) {
        // Hardcoded National ID until login system is in place
        PatientDTO patient = patientService.findPatientByNationalID(123456789L);
        model.addAttribute("patient", patient);
        return "patient/list";
    }

    @GetMapping("/fragment/{id}")
    public String getPatientFragment(@PathVariable Long id, Model model) {
        PatientDTO patient = patientService.findPatientByNationalID(id);
        model.addAttribute("patient", patient);
        return "fragments/patient :: patientFragment";
    }
}
