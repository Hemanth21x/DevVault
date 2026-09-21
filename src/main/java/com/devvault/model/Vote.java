package com.devvault.model;

import java.time.LocalDateTime;

public class Vote {

    private int id;
    private int solutionId;
    private int userId;
    private String voteType;
    private LocalDateTime createdAt;

    public Vote() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getSolutionId() { return solutionId; }
    public void setSolutionId(int solutionId) { this.solutionId = solutionId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getVoteType() { return voteType; }
    public void setVoteType(String voteType) { this.voteType = voteType; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
