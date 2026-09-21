package com.devvault.model;

import java.time.LocalDateTime;

public class Error {

    private int id;
    private String title;
    private String errorMessage;
    private String description;
    private String cause;
    private int technologyId;
    private int categoryId;
    private int postedBy;
    private LocalDateTime createdAt;

    // Display-only fields, filled by JOIN queries
    private String technologyName;
    private String categoryName;
    private String postedByName;
    private int solutionCount;

    public Error() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getErrorMessage() { return errorMessage; }
    public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCause() { return cause; }
    public void setCause(String cause) { this.cause = cause; }

    public int getTechnologyId() { return technologyId; }
    public void setTechnologyId(int technologyId) { this.technologyId = technologyId; }

    public int getCategoryId() { return categoryId; }
    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }

    public int getPostedBy() { return postedBy; }
    public void setPostedBy(int postedBy) { this.postedBy = postedBy; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public String getTechnologyName() { return technologyName; }
    public void setTechnologyName(String technologyName) { this.technologyName = technologyName; }

    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }

    public String getPostedByName() { return postedByName; }
    public void setPostedByName(String postedByName) { this.postedByName = postedByName; }

    public int getSolutionCount() { return solutionCount; }
    public void setSolutionCount(int solutionCount) { this.solutionCount = solutionCount; }
}
