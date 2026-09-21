package com.devvault.service;

import com.devvault.dao.DebugJournalDAO;
import com.devvault.model.DebugJournal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DebugJournalService {

    @Autowired
    private DebugJournalDAO debugJournalDAO;

    public int saveJournal(DebugJournal journal) {
        if (journal.getProblem() == null || journal.getProblem().trim().isEmpty()) {
            throw new IllegalArgumentException("Problem field cannot be empty");
        }
        return debugJournalDAO.save(journal);
    }

    public List<DebugJournal> getJournalsForUser(int userId) { return debugJournalDAO.findByUserId(userId); }
    public DebugJournal getJournalById(int id) { return debugJournalDAO.findById(id); }
    public void deleteJournal(int id) { debugJournalDAO.delete(id); }
}
