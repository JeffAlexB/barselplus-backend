package com.alex.barselplus_backend.views;

import com.alex.barselplus_backend.dto.MedicalHistoryDTO;
import com.alex.barselplus_backend.service.MedicalHistoryService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/medical-history")
public class MedicalHistoryViewController {

    private final MedicalHistoryService service;

    public MedicalHistoryViewController(MedicalHistoryService service) {
        this.service = service;
    }

    @GetMapping("/fragment/{pregnancyId}")
    public String load(@PathVariable Long pregnancyId, Model model) {
        try {
            MedicalHistoryDTO dto = service.getMedicalHistoryByID(pregnancyId);
            model.addAttribute("history", dto);
            model.addAttribute("pregnancyId", pregnancyId);
            return "fragments/medical_history :: viewHistory";
        } catch (EntityNotFoundException e) {
            model.addAttribute("pregnancyId", pregnancyId);
            return "fragments/medical_history :: emptyHistory";
        }
    }
}
