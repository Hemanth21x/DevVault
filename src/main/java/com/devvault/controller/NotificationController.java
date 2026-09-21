package com.devvault.controller;

import com.devvault.service.NotificationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping
    public String list(HttpSession session, Model model) {
        Integer userId = (Integer) session.getAttribute("userId");
        model.addAttribute("notifications", notificationService.getNotificationsForUser(userId));
        notificationService.markAllAsRead(userId);
        return "notifications";
    }

    @GetMapping("/unread-count")
    @ResponseBody
    public String unreadCount(HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) return "{\"count\":0}";
        return "{\"count\":" + notificationService.getUnreadCount(userId) + "}";
    }
}
