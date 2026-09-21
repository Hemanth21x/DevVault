package com.devvault.controller;

import com.devvault.service.ErrorService;
import com.devvault.service.LookupService;
import com.devvault.service.SolutionService;
import com.devvault.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired private ErrorService errorService;
    @Autowired private SolutionService solutionService;
    @Autowired private UserService userService;
    @Autowired private LookupService lookupService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("totalErrors", errorService.countErrors());
        model.addAttribute("totalSolutions", solutionService.countSolutions());
        model.addAttribute("totalUsers", userService.countUsers());
        model.addAttribute("recentErrors", errorService.getAllErrors().stream().limit(5).toList());
        model.addAttribute("technologies", lookupService.getTechnologies());
        return "home";
    }
}
