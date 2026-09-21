package com.devvault.controller;

import com.devvault.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired private UserService userService;
    @Autowired private ErrorService errorService;
    @Autowired private SolutionService solutionService;
    @Autowired private LookupService lookupService;

    @GetMapping
    public String dashboard(Model model) {
        model.addAttribute("totalUsers", userService.countUsers());
        model.addAttribute("totalErrors", errorService.countErrors());
        model.addAttribute("totalSolutions", solutionService.countSolutions());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("techStats", lookupService.getErrorCountByTechnology());
        return "admin-dashboard";
    }

    @PostMapping("/users/{id}/role")
    public String changeRole(@PathVariable("id") int id, @RequestParam("roleId") int roleId) {
        userService.changeRole(id, roleId);
        return "redirect:/admin";
    }

    @PostMapping("/errors/{id}/delete")
    public String deleteError(@PathVariable("id") int id) {
        errorService.deleteError(id);
        return "redirect:/admin";
    }
}
