package com.devvault.dao;

import com.devvault.model.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VoteDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Vote> mapper = (rs, rowNum) -> {
        Vote v = new Vote();
        v.setId(rs.getInt("id"));
        v.setSolutionId(rs.getInt("solution_id"));
        v.setUserId(rs.getInt("user_id"));
        v.setVoteType(rs.getString("vote_type"));
        if (rs.getTimestamp("created_at") != null) {
            v.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        }
        return v;
    };

    public int save(Vote vote) {
        return jdbcTemplate.update("INSERT INTO votes (solution_id, user_id, vote_type) VALUES (?, ?, ?)",
                vote.getSolutionId(), vote.getUserId(), vote.getVoteType());
    }

    public Vote findByUserAndSolution(int userId, int solutionId) {
        List<Vote> r = jdbcTemplate.query("SELECT * FROM votes WHERE user_id = ? AND solution_id = ?", mapper, userId, solutionId);
        return r.isEmpty() ? null : r.get(0);
    }

    public int getScore(int solutionId) {
        Integer s = jdbcTemplate.queryForObject(
            "SELECT COALESCE(SUM(CASE WHEN vote_type='UP' THEN 1 ELSE -1 END), 0) FROM votes WHERE solution_id = ?",
            Integer.class, solutionId);
        return s == null ? 0 : s;
    }

    public int updateVoteType(int userId, int solutionId, String newVoteType) {
        return jdbcTemplate.update("UPDATE votes SET vote_type = ? WHERE user_id = ? AND solution_id = ?",
                newVoteType, userId, solutionId);
    }

    public int delete(int userId, int solutionId) {
        return jdbcTemplate.update("DELETE FROM votes WHERE user_id = ? AND solution_id = ?", userId, solutionId);
    }
}
