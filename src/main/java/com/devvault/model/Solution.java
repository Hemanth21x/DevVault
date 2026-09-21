package com.devvault.model;

import java.time.LocalDateTime;

public class Solution {

    private int id;
    private int errorId;
    private int postedBy;
    private String solutionText;
    private String codeExample;
    private boolean accepted;
    private LocalDateTime createdAt;

    private String postedByName;
    private int score;

    public Solution() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getErrorId() { return errorId; }
    public void setErrorId(int errorId) { this.errorId = errorId; }

    public int getPostedBy() { return postedBy; }
    public void setPostedBy(int postedBy) { this.postedBy = postedBy; }

    public String getSolutionText() { return solutionText; }
    public void setSolutionText(String solutionText) { this.solutionText = solutionText; }

    public String getCodeExample() { return codeExample; }
    public void setCodeExample(String codeExample) { this.codeExample = codeExample; }

    public boolean isAccepted() { return accepted; }
    public void setAccepted(boolean accepted) { this.accepted = accepted; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public String getPostedByName() { return postedByName; }
    public void setPostedByName(String postedByName) { this.postedByName = postedByName; }

    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
}
