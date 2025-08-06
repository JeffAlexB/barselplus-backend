package com.alex.barselplus_backend.views;

import com.alex.barselplus_backend.dto.PartnerDTO;
import com.alex.barselplus_backend.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/partner")
public class PartnerViewController {

    private final PartnerService partnerService;

    @Autowired
    public PartnerViewController(PartnerService partnerService) {
        this.partnerService = partnerService;
    }

    @GetMapping
    public String getPartnerDashboard(Model model) {
        // Hardcoded National ID until login system is in place
        PartnerDTO partner = partnerService.getPartnerByPatientId(123456789L);
        model.addAttribute("partner", partner);
        return "partner/list";
    }

    @GetMapping("/fragment/{id}")
    public String getPartnerFragment(@PathVariable Long id, Model model) {
        PartnerDTO partner = partnerService.getPartnerByPatientId(id);
        model.addAttribute("partner", partner);
        return "fragments/partner :: partnerFragment"; // Thymeleaf fragment reference
    }
}
