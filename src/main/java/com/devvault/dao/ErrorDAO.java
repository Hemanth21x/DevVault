package com.devvault.dao;

import com.devvault.model.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class ErrorDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String BASE_SELECT =
        "SELECT e.*, t.name AS tech_name, c.name AS cat_name, u.name AS user_name, " +
        "(SELECT COUNT(*) FROM solutions s WHERE s.error_id = e.id) AS solution_count " +
        "FROM errors e " +
        "LEFT JOIN technologies t ON e.technology_id = t.id " +
        "LEFT JOIN categories c ON e.category_id = c.id " +
        "LEFT JOIN users u ON e.posted_by = u.id ";

    private RowMapper<Error> mapper = (rs, rowNum) -> {
        Error e = new Error();
        e.setId(rs.getInt("id"));
        e.setTitle(rs.getString("title"));
        e.setErrorMessage(rs.getString("error_message"));
        e.setDescription(rs.getString("description"));
        e.setCause(rs.getString("cause"));
        e.setTechnologyId(rs.getInt("technology_id"));
        e.setCategoryId(rs.getInt("category_id"));
        e.setPostedBy(rs.getInt("posted_by"));
        if (rs.getTimestamp("created_at") != null) {
            e.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        }
        e.setTechnologyName(rs.getString("tech_name"));
        e.setCategoryName(rs.getString("cat_name"));
        e.setPostedByName(rs.getString("user_name"));
        e.setSolutionCount(rs.getInt("solution_count"));
        return e;
    };

    public int save(Error error) {
        String sql = "INSERT INTO errors (title, error_message, description, cause, technology_id, category_id, posted_by) VALUES (?, ?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, error.getTitle());
            ps.setString(2, error.getErrorMessage());
            ps.setString(3, error.getDescription());
            ps.setString(4, error.getCause());
            ps.setInt(5, error.getTechnologyId());
            ps.setInt(6, error.getCategoryId());
            ps.setInt(7, error.getPostedBy());
            return ps;
        }, keyHolder);
        return keyHolder.getKey() == null ? 0 : keyHolder.getKey().intValue();
    }

    public Error findById(int id) {
        List<Error> r = jdbcTemplate.query(BASE_SELECT + "WHERE e.id = ?", mapper, id);
        return r.isEmpty() ? null : r.get(0);
    }

    public List<Error> findAll() {
        return jdbcTemplate.query(BASE_SELECT + "ORDER BY e.created_at DESC", mapper);
    }

    public List<Error> findPaged(int limit, int offset) {
        return jdbcTemplate.query(BASE_SELECT + "ORDER BY e.created_at DESC LIMIT ? OFFSET ?", mapper, limit, offset);
    }

    public List<Error> search(String keyword) {
        String p = "%" + keyword + "%";
        return jdbcTemplate.query(
            BASE_SELECT + "WHERE e.title LIKE ? OR e.error_message LIKE ? OR e.description LIKE ? ORDER BY e.created_at DESC",
            mapper, p, p, p);
    }

    public List<Error> filter(Integer technologyId, Integer categoryId) {
        StringBuilder sql = new StringBuilder(BASE_SELECT + "WHERE 1=1 ");
        if (technologyId != null && technologyId > 0) sql.append("AND e.technology_id = ").append(technologyId).append(" ");
        if (categoryId != null && categoryId > 0) sql.append("AND e.category_id = ").append(categoryId).append(" ");
        sql.append("ORDER BY e.created_at DESC");
        return jdbcTemplate.query(sql.toString(), mapper);
    }

    public List<Error> findByUser(int userId) {
        return jdbcTemplate.query(BASE_SELECT + "WHERE e.posted_by = ? ORDER BY e.created_at DESC", mapper, userId);
    }

    public int countAll() {
        Integer c = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM errors", Integer.class);
        return c == null ? 0 : c;
    }

    public int update(Error error) {
        String sql = "UPDATE errors SET title=?, error_message=?, description=?, cause=?, technology_id=?, category_id=? WHERE id=?";
        return jdbcTemplate.update(sql, error.getTitle(), error.getErrorMessage(), error.getDescription(),
                error.getCause(), error.getTechnologyId(), error.getCategoryId(), error.getId());
    }

    public int delete(int id) {
        return jdbcTemplate.update("DELETE FROM errors WHERE id = ?", id);
    }
}
