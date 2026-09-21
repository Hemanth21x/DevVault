package com.devvault.model;

import java.time.LocalDateTime;

public class DebugJournal {

    private int id;
    private int userId;
    private String projectName;
    private String problem;
    private String rootCause;
    private String solution;
    private int timeTakenMinutes;
    private LocalDateTime createdAt;

    public DebugJournal() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getProjectName() { return projectName; }
    public void setProjectName(String projectName) { this.projectName = projectName; }

    public String getProblem() { return problem; }
    public void setProblem(String problem) { this.problem = problem; }

    public String getRootCause() { return rootCause; }
    public void setRootCause(String rootCause) { this.rootCause = rootCause; }

    public String getSolution() { return solution; }
    public void setSolution(String solution) { this.solution = solution; }

    public int getTimeTakenMinutes() { return timeTakenMinutes; }
    public void setTimeTakenMinutes(int timeTakenMinutes) { this.timeTakenMinutes = timeTakenMinutes; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
