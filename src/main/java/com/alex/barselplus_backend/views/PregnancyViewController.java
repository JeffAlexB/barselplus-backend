package com.alex.barselplus_backend.views;

import com.alex.barselplus_backend.service.PregnancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/pregnancies")
public class PregnancyViewController {
    private final PregnancyService pregnancyService;

    @Autowired
    public PregnancyViewController(PregnancyService pregnancyService) {
        this.pregnancyService = pregnancyService;
    }

    // Full page view
    @GetMapping
    public String getPregnanciesPage(@RequestParam(name="patientId", required=false) Long patientId,
                                  Model model) {
        // hardcoded for testing
        long pid = (patientId != null) ? patientId : 1L;
        model.addAttribute("patientId", pid);
        model.addAttribute("pregnancies", pregnancyService.getAllPregnanciesByPatient(pid));
        return "pregnancy/list";
    }

    // HTMX fragments
    @GetMapping("/fragment/{patientId}")
    public String pregnanciesFragment(@PathVariable Long patientId, Model model) {
        model.addAttribute("patientId", patientId);
        model.addAttribute("pregnancies", pregnancyService.getAllPregnanciesByPatient(patientId));
        return "fragments/pregnancy :: list";
    }

    // HTMX 'detail' fragments
    @GetMapping("/detail/{pregnancyId}")
    public String pregnancyDetail(@PathVariable Long pregnancyId, Model model) {
        model.addAttribute("pregnancy", pregnancyService.getById(pregnancyId));
        return "fragments/pregnancy :: detail";
    }
}
