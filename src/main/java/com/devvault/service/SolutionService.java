package com.devvault.service;

import com.devvault.dao.ErrorDAO;
import com.devvault.dao.SolutionDAO;
import com.devvault.model.Error;
import com.devvault.model.Solution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolutionService {

    @Autowired
    private SolutionDAO solutionDAO;

    @Autowired
    private ErrorDAO errorDAO;

    @Autowired
    private NotificationService notificationService;

    public int submitSolution(Solution solution) {
        if (solution.getSolutionText() == null || solution.getSolutionText().trim().isEmpty()) {
            throw new IllegalArgumentException("Solution text cannot be empty");
        }
        int result = solutionDAO.save(solution);

        Error error = errorDAO.findById(solution.getErrorId());
        if (error != null && error.getPostedBy() != solution.getPostedBy()) {
            notificationService.notify(error.getPostedBy(),
                    "Someone posted a solution to your error: " + error.getTitle());
        }
        return result;
    }

    public List<Solution> getSolutionsForError(int errorId) { return solutionDAO.findByErrorId(errorId); }
    public List<Solution> getSolutionsByUser(int userId) { return solutionDAO.findByUser(userId); }
    public int countSolutions() { return solutionDAO.countAll(); }

    public void acceptSolution(int solutionId) {
        Solution s = solutionDAO.findById(solutionId);
        solutionDAO.markAsAccepted(solutionId);
        if (s != null) {
            notificationService.notify(s.getPostedBy(), "Your solution was marked as accepted!");
        }
    }

    public void deleteSolution(int id) { solutionDAO.delete(id); }
}
