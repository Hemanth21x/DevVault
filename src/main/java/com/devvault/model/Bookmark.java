package com.devvault.model;

import java.time.LocalDateTime;

public class Bookmark {

    private int id;
    private int userId;
    private Integer errorId;
    private Integer solutionId;
    private LocalDateTime createdAt;

    private String errorTitle;

    public Bookmark() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public Integer getErrorId() { return errorId; }
    public void setErrorId(Integer errorId) { this.errorId = errorId; }

    public Integer getSolutionId() { return solutionId; }
    public void setSolutionId(Integer solutionId) { this.solutionId = solutionId; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public String getErrorTitle() { return errorTitle; }
    public void setErrorTitle(String errorTitle) { this.errorTitle = errorTitle; }
}
