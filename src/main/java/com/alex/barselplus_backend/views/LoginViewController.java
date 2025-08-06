package com.alex.barselplus_backend.views;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginViewController {

    @GetMapping("/")
    public String showLoginPage() {
        return "login";
    }
}
