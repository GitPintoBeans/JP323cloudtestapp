package edu.gcu.cst323.cloudtestapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

        // Serve the application home page
    @GetMapping("/")
    public String home() {
        return "index";
    }
}
