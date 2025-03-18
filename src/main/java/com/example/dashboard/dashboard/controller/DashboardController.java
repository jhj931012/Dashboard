package com.example.dashboard.dashboard.controller;

import com.example.dashboard.dashboard.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        model.addAttribute("dashboardData", dashboardService.getDashboardData());
        return "dashboard/index"; // Thymeleaf 템플릿 (src/main/resources/templates/dashboard/index.html)
    }
}
