package com.devvault.controller;

import com.devvault.service.BookmarkService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/bookmarks")
public class BookmarkController {

    @Autowired
    private BookmarkService bookmarkService;

    @GetMapping
    public String list(HttpSession session, Model model) {
        Integer userId = (Integer) session.getAttribute("userId");
        model.addAttribute("bookmarks", bookmarkService.getBookmarksForUser(userId));
        return "bookmarks";
    }

    @PostMapping("/error/{errorId}")
    @ResponseBody
    public String toggle(@PathVariable("errorId") int errorId, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) return "{\"status\":\"unauthorized\"}";
        boolean added = bookmarkService.toggleErrorBookmark(userId, errorId);
        return "{\"status\":\"" + (added ? "added" : "exists") + "\"}";
    }

    @PostMapping("/{id}/delete")
    public String remove(@PathVariable("id") int id) {
        bookmarkService.removeBookmark(id);
        return "redirect:/bookmarks";
    }
}
