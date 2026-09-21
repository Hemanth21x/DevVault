package com.devvault.service;

import com.devvault.dao.ErrorDAO;
import com.devvault.model.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ErrorService {

    @Autowired
    private ErrorDAO errorDAO;

    public int createError(Error error) {
        if (error.getTitle() == null || error.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Error title cannot be empty");
        }
        if (error.getErrorMessage() == null || error.getErrorMessage().trim().isEmpty()) {
            throw new IllegalArgumentException("Error message cannot be empty");
        }
        return errorDAO.save(error);
    }

    public Error getErrorById(int id) { return errorDAO.findById(id); }
    public List<Error> getAllErrors() { return errorDAO.findAll(); }
    public List<Error> getErrorsByUser(int userId) { return errorDAO.findByUser(userId); }
    public int countErrors() { return errorDAO.countAll(); }

    public List<Error> searchErrors(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) return errorDAO.findAll();
        return errorDAO.search(keyword.trim());
    }

    public List<Error> filterErrors(Integer technologyId, Integer categoryId) {
        return errorDAO.filter(technologyId, categoryId);
    }

    public void updateError(Error error) { errorDAO.update(error); }
    public void deleteError(int id) { errorDAO.delete(id); }
}
