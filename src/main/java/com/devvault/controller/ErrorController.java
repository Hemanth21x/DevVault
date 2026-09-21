package com.devvault.controller;

import com.devvault.model.Error;
import com.devvault.model.Solution;
import com.devvault.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/errors")
public class ErrorController {

    @Autowired private ErrorService errorService;
    @Autowired private SolutionService solutionService;
    @Autowired private LookupService lookupService;

    @GetMapping
    public String listErrors(
            @RequestParam(value = "q", required = false) String q,
            @RequestParam(value = "tech", required = false) Integer tech,
            @RequestParam(value = "cat", required = false) Integer cat,
            Model model) {

        List<Error> errors;
        if (q != null && !q.trim().isEmpty()) {
            errors = errorService.searchErrors(q);
        } else if ((tech != null && tech > 0) || (cat != null && cat > 0)) {
            errors = errorService.filterErrors(tech, cat);
        } else {
            errors = errorService.getAllErrors();
        }

        model.addAttribute("errors", errors);
        model.addAttribute("technologies", lookupService.getTechnologies());
        model.addAttribute("categories", lookupService.getCategories());
        model.addAttribute("q", q);
        model.addAttribute("selectedTech", tech);
        model.addAttribute("selectedCat", cat);
        return "error-list";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("technologies", lookupService.getTechnologies());
        model.addAttribute("categories", lookupService.getCategories());
        return "error-form";
    }

    @PostMapping("/new")
    public String addError(
            @RequestParam("title") String title,
            @RequestParam("errorMessage") String errorMessage,
            @RequestParam("description") String description,
            @RequestParam("cause") String cause,
            @RequestParam("technologyId") int technologyId,
            @RequestParam("categoryId") int categoryId,
            HttpSession session,
            Model model) {

        Integer userId = (Integer) session.getAttribute("userId");

        Error error = new Error();
        error.setTitle(title);
        error.setErrorMessage(errorMessage);
        error.setDescription(description);
        error.setCause(cause);
        error.setTechnologyId(technologyId);
        error.setCategoryId(categoryId);
        error.setPostedBy(userId);

        try {
            int newId = errorService.createError(error);
            return "redirect:/errors/" + newId;
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("technologies", lookupService.getTechnologies());
            model.addAttribute("categories", lookupService.getCategories());
            return "error-form";
        }
    }

    @GetMapping("/{id}")
    public String errorDetails(@PathVariable("id") int id, Model model) {
        Error error = errorService.getErrorById(id);
        if (error == null) return "redirect:/errors";
        model.addAttribute("err", error);
        model.addAttribute("solutions", solutionService.getSolutionsForError(id));
        return "error-details";
    }

    @PostMapping("/{id}/solutions")
    public String addSolution(
            @PathVariable("id") int errorId,
            @RequestParam("solutionText") String solutionText,
            @RequestParam(value = "codeExample", required = false) String codeExample,
            HttpSession session) {

        Integer userId = (Integer) session.getAttribute("userId");

        Solution s = new Solution();
        s.setErrorId(errorId);
        s.setPostedBy(userId);
        s.setSolutionText(solutionText);
        s.setCodeExample(codeExample);

        solutionService.submitSolution(s);
        return "redirect:/errors/" + errorId;
    }

    @PostMapping("/{errorId}/solutions/{solutionId}/accept")
    public String acceptSolution(@PathVariable("errorId") int errorId,
                                 @PathVariable("solutionId") int solutionId,
                                 HttpSession session) {
        Error error = errorService.getErrorById(errorId);
        Integer userId = (Integer) session.getAttribute("userId");
        if (error != null && userId != null && error.getPostedBy() == userId) {
            solutionService.acceptSolution(solutionId);
        }
        return "redirect:/errors/" + errorId;
    }
}
