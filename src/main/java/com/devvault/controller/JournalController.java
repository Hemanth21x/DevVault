package com.devvault.controller;

import com.devvault.model.DebugJournal;
import com.devvault.service.DebugJournalService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/journal")
public class JournalController {

    @Autowired
    private DebugJournalService journalService;

    @GetMapping
    public String listJournals(HttpSession session, Model model) {
        Integer userId = (Integer) session.getAttribute("userId");
        model.addAttribute("journals", journalService.getJournalsForUser(userId));
        return "journal-list";
    }

    @PostMapping("/new")
    public String addJournal(
            @RequestParam("projectName") String projectName,
            @RequestParam("problem") String problem,
            @RequestParam("rootCause") String rootCause,
            @RequestParam("solution") String solution,
            @RequestParam("timeTakenMinutes") int timeTakenMinutes,
            HttpSession session) {

        Integer userId = (Integer) session.getAttribute("userId");

        DebugJournal j = new DebugJournal();
        j.setUserId(userId);
        j.setProjectName(projectName);
        j.setProblem(problem);
        j.setRootCause(rootCause);
        j.setSolution(solution);
        j.setTimeTakenMinutes(timeTakenMinutes);

        journalService.saveJournal(j);
        return "redirect:/journal";
    }

    @PostMapping("/{id}/delete")
    public String deleteJournal(@PathVariable("id") int id) {
        journalService.deleteJournal(id);
        return "redirect:/journal";
    }
}
