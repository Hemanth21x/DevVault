package com.devvault.service;

import com.devvault.dao.BookmarkDAO;
import com.devvault.model.Bookmark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookmarkService {

    @Autowired
    private BookmarkDAO bookmarkDAO;

    public boolean toggleErrorBookmark(int userId, int errorId) {
        if (bookmarkDAO.exists(userId, errorId)) {
            return false;
        }
        bookmarkDAO.saveErrorBookmark(userId, errorId);
        return true;
    }

    public List<Bookmark> getBookmarksForUser(int userId) { return bookmarkDAO.findByUserId(userId); }
    public int countForUser(int userId) { return bookmarkDAO.countByUser(userId); }
    public void removeBookmark(int id) { bookmarkDAO.delete(id); }
}
