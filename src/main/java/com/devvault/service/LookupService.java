package com.devvault.service;

import com.devvault.dao.LookupDAO;
import com.devvault.model.Category;
import com.devvault.model.Technology;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class LookupService {

    @Autowired
    private LookupDAO lookupDAO;

    public List<Technology> getTechnologies() { return lookupDAO.findAllTechnologies(); }
    public List<Category> getCategories() { return lookupDAO.findAllCategories(); }
    public List<Map<String, Object>> getErrorCountByTechnology() { return lookupDAO.errorCountByTechnology(); }
}
