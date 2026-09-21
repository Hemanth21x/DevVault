package com.devvault.service;

import com.devvault.dao.VoteDAO;
import com.devvault.model.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteService {

    @Autowired
    private VoteDAO voteDAO;

    public int castVote(int userId, int solutionId, String voteType) {
        Vote existing = voteDAO.findByUserAndSolution(userId, solutionId);

        if (existing == null) {
            Vote v = new Vote();
            v.setUserId(userId);
            v.setSolutionId(solutionId);
            v.setVoteType(voteType);
            voteDAO.save(v);
        } else if (existing.getVoteType().equals(voteType)) {
            voteDAO.delete(userId, solutionId);
        } else {
            voteDAO.updateVoteType(userId, solutionId, voteType);
        }
        return voteDAO.getScore(solutionId);
    }

    public int getScore(int solutionId) { return voteDAO.getScore(solutionId); }
}
