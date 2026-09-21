package com.devvault.dao;

import com.devvault.model.Bookmark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookmarkDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Bookmark> mapper = (rs, rowNum) -> {
        Bookmark b = new Bookmark();
        b.setId(rs.getInt("id"));
        b.setUserId(rs.getInt("user_id"));

        int errorId = rs.getInt("error_id");
        b.setErrorId(rs.wasNull() ? null : errorId);

        int solutionId = rs.getInt("solution_id");
        b.setSolutionId(rs.wasNull() ? null : solutionId);

        if (rs.getTimestamp("created_at") != null) {
            b.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        }
        try {
            b.setErrorTitle(rs.getString("error_title"));
        } catch (Exception ignored) {}
        return b;
    };

    public int saveErrorBookmark(int userId, int errorId) {
        return jdbcTemplate.update("INSERT INTO bookmarks (user_id, error_id) VALUES (?, ?)", userId, errorId);
    }

    public boolean exists(int userId, int errorId) {
        Integer c = jdbcTemplate.queryForObject(
            "SELECT COUNT(*) FROM bookmarks WHERE user_id = ? AND error_id = ?", Integer.class, userId, errorId);
        return c != null && c > 0;
    }

    public List<Bookmark> findByUserId(int userId) {
        String sql = "SELECT b.*, e.title AS error_title FROM bookmarks b " +
                     "LEFT JOIN errors e ON b.error_id = e.id " +
                     "WHERE b.user_id = ? ORDER BY b.created_at DESC";
        return jdbcTemplate.query(sql, mapper, userId);
    }

    public int countByUser(int userId) {
        Integer c = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM bookmarks WHERE user_id = ?", Integer.class, userId);
        return c == null ? 0 : c;
    }

    public int delete(int id) {
        return jdbcTemplate.update("DELETE FROM bookmarks WHERE id = ?", id);
    }
}
