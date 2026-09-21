package com.devvault.dao;

import com.devvault.model.DebugJournal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DebugJournalDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<DebugJournal> mapper = (rs, rowNum) -> {
        DebugJournal j = new DebugJournal();
        j.setId(rs.getInt("id"));
        j.setUserId(rs.getInt("user_id"));
        j.setProjectName(rs.getString("project_name"));
        j.setProblem(rs.getString("problem"));
        j.setRootCause(rs.getString("root_cause"));
        j.setSolution(rs.getString("solution"));
        j.setTimeTakenMinutes(rs.getInt("time_taken_minutes"));
        if (rs.getTimestamp("created_at") != null) {
            j.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        }
        return j;
    };

    public int save(DebugJournal j) {
        String sql = "INSERT INTO debug_journals (user_id, project_name, problem, root_cause, solution, time_taken_minutes) VALUES (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, j.getUserId(), j.getProjectName(), j.getProblem(),
                j.getRootCause(), j.getSolution(), j.getTimeTakenMinutes());
    }

    public List<DebugJournal> findByUserId(int userId) {
        return jdbcTemplate.query("SELECT * FROM debug_journals WHERE user_id = ? ORDER BY created_at DESC", mapper, userId);
    }

    public DebugJournal findById(int id) {
        List<DebugJournal> r = jdbcTemplate.query("SELECT * FROM debug_journals WHERE id = ?", mapper, id);
        return r.isEmpty() ? null : r.get(0);
    }

    public int delete(int id) {
        return jdbcTemplate.update("DELETE FROM debug_journals WHERE id = ?", id);
    }
}
