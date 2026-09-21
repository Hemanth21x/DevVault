package com.devvault.dao;

import com.devvault.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<User> mapper = (rs, rowNum) -> {
        User u = new User();
        u.setId(rs.getInt("id"));
        u.setName(rs.getString("name"));
        u.setEmail(rs.getString("email"));
        u.setPassword(rs.getString("password"));
        u.setRoleId(rs.getInt("role_id"));
        if (rs.getTimestamp("created_at") != null) {
            u.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        }
        return u;
    };

    public int save(User user) {
        String sql = "INSERT INTO users (name, email, password, role_id) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, user.getName(), user.getEmail(), user.getPassword(), user.getRoleId());
    }

    public User findById(int id) {
        List<User> r = jdbcTemplate.query("SELECT * FROM users WHERE id = ?", mapper, id);
        return r.isEmpty() ? null : r.get(0);
    }

    public User findByEmail(String email) {
        List<User> r = jdbcTemplate.query("SELECT * FROM users WHERE email = ?", mapper, email);
        return r.isEmpty() ? null : r.get(0);
    }

    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM users ORDER BY created_at DESC", mapper);
    }

    public int countAll() {
        Integer c = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM users", Integer.class);
        return c == null ? 0 : c;
    }

    public int updateRole(int userId, int roleId) {
        return jdbcTemplate.update("UPDATE users SET role_id = ? WHERE id = ?", roleId, userId);
    }

    public int delete(int id) {
        return jdbcTemplate.update("DELETE FROM users WHERE id = ?", id);
    }
}
