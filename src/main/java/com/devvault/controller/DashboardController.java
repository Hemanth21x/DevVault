package com.devvault.controller;

import com.devvault.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @Autowired private ErrorService errorService;
    @Autowired private SolutionService solutionService;
    @Autowired private BookmarkService bookmarkService;
    @Autowired private DebugJournalService journalService;
    @Autowired private NotificationService notificationService;

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        Integer userId = (Integer) session.getAttribute("userId");

        model.addAttribute("myErrors", errorService.getErrorsByUser(userId));
        model.addAttribute("mySolutions", solutionService.getSolutionsByUser(userId));
        model.addAttribute("myBookmarkCount", bookmarkService.countForUser(userId));
        model.addAttribute("myJournals", journalService.getJournalsForUser(userId));
        model.addAttribute("unreadCount", notificationService.getUnreadCount(userId));
        return "dashboard";
    }
}
