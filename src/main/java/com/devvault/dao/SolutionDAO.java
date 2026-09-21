package com.devvault.dao;

import com.devvault.model.Solution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SolutionDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String BASE_SELECT =
        "SELECT s.*, u.name AS user_name, " +
        "COALESCE((SELECT SUM(CASE WHEN v.vote_type='UP' THEN 1 ELSE -1 END) FROM votes v WHERE v.solution_id = s.id), 0) AS score " +
        "FROM solutions s LEFT JOIN users u ON s.posted_by = u.id ";

    private RowMapper<Solution> mapper = (rs, rowNum) -> {
        Solution s = new Solution();
        s.setId(rs.getInt("id"));
        s.setErrorId(rs.getInt("error_id"));
        s.setPostedBy(rs.getInt("posted_by"));
        s.setSolutionText(rs.getString("solution_text"));
        s.setCodeExample(rs.getString("code_example"));
        s.setAccepted(rs.getBoolean("is_accepted"));
        if (rs.getTimestamp("created_at") != null) {
            s.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        }
        s.setPostedByName(rs.getString("user_name"));
        s.setScore(rs.getInt("score"));
        return s;
    };

    public int save(Solution solution) {
        String sql = "INSERT INTO solutions (error_id, posted_by, solution_text, code_example) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, solution.getErrorId(), solution.getPostedBy(),
                solution.getSolutionText(), solution.getCodeExample());
    }

    public Solution findById(int id) {
        List<Solution> r = jdbcTemplate.query(BASE_SELECT + "WHERE s.id = ?", mapper, id);
        return r.isEmpty() ? null : r.get(0);
    }

    public List<Solution> findByErrorId(int errorId) {
        return jdbcTemplate.query(BASE_SELECT + "WHERE s.error_id = ? ORDER BY s.is_accepted DESC, score DESC, s.created_at ASC", mapper, errorId);
    }

    public List<Solution> findByUser(int userId) {
        return jdbcTemplate.query(BASE_SELECT + "WHERE s.posted_by = ? ORDER BY s.created_at DESC", mapper, userId);
    }

    public int countAll() {
        Integer c = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM solutions", Integer.class);
        return c == null ? 0 : c;
    }

    public int markAsAccepted(int solutionId) {
        return jdbcTemplate.update("UPDATE solutions SET is_accepted = TRUE WHERE id = ?", solutionId);
    }

    public int delete(int id) {
        return jdbcTemplate.update("DELETE FROM solutions WHERE id = ?", id);
    }
}
