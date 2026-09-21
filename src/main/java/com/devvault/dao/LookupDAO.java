package com.devvault.dao;

import com.devvault.model.Category;
import com.devvault.model.Technology;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class LookupDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Technology> techMapper = (rs, n) -> new Technology(rs.getInt("id"), rs.getString("name"));
    private RowMapper<Category> catMapper = (rs, n) -> new Category(rs.getInt("id"), rs.getString("name"));

    public List<Technology> findAllTechnologies() {
        return jdbcTemplate.query("SELECT * FROM technologies ORDER BY name", techMapper);
    }

    public List<Category> findAllCategories() {
        return jdbcTemplate.query("SELECT * FROM categories ORDER BY name", catMapper);
    }

    public List<Map<String, Object>> errorCountByTechnology() {
        return jdbcTemplate.queryForList(
            "SELECT t.name AS name, COUNT(e.id) AS total FROM technologies t " +
            "LEFT JOIN errors e ON e.technology_id = t.id GROUP BY t.id, t.name ORDER BY total DESC");
    }
}
