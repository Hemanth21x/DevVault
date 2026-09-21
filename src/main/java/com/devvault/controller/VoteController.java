package com.devvault.controller;

import com.devvault.service.VoteService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class VoteController {

    @Autowired
    private VoteService voteService;

    /** AJAX endpoint: returns the new score as JSON */
    @PostMapping("/vote/{solutionId}")
    @ResponseBody
    public String vote(@PathVariable("solutionId") int solutionId,
                       @RequestParam("type") String type,
                       HttpSession session) {

        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) return "{\"error\":\"unauthorized\"}";

        int newScore = voteService.castVote(userId, solutionId, type);
        return "{\"score\":" + newScore + "}";
    }
}
