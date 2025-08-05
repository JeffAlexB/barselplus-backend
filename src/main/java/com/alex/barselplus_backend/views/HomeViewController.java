package com.alex.barselplus_backend.views;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeViewController {

    @GetMapping("/")
    public String homePage() {
        return "dashboard";
    }

    @GetMapping("/home")
    public String redirectToRoot() {
        return "redirect:/";
    }
}
