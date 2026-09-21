package com.devvault.dao;

import com.devvault.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NotificationDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Notification> mapper = (rs, rowNum) -> {
        Notification n = new Notification();
        n.setId(rs.getInt("id"));
        n.setUserId(rs.getInt("user_id"));
        n.setMessage(rs.getString("message"));
        n.setRead(rs.getBoolean("is_read"));
        if (rs.getTimestamp("created_at") != null) {
            n.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        }
        return n;
    };

    public int save(Notification n) {
        return jdbcTemplate.update("INSERT INTO notifications (user_id, message) VALUES (?, ?)", n.getUserId(), n.getMessage());
    }

    public List<Notification> findByUserId(int userId) {
        return jdbcTemplate.query("SELECT * FROM notifications WHERE user_id = ? ORDER BY created_at DESC", mapper, userId);
    }

    public int countUnread(int userId) {
        Integer c = jdbcTemplate.queryForObject(
            "SELECT COUNT(*) FROM notifications WHERE user_id = ? AND is_read = FALSE", Integer.class, userId);
        return c == null ? 0 : c;
    }

    public int markAsRead(int notificationId) {
        return jdbcTemplate.update("UPDATE notifications SET is_read = TRUE WHERE id = ?", notificationId);
    }

    public int markAllAsRead(int userId) {
        return jdbcTemplate.update("UPDATE notifications SET is_read = TRUE WHERE user_id = ?", userId);
    }
}
