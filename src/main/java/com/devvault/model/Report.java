package com.devvault.model;

import java.time.LocalDateTime;

public class Report {

    private int id;
    private int reportedBy;
    private Integer errorId;
    private Integer solutionId;
    private String reason;
    private String status;
    private LocalDateTime createdAt;

    public Report() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getReportedBy() { return reportedBy; }
    public void setReportedBy(int reportedBy) { this.reportedBy = reportedBy; }

    public Integer getErrorId() { return errorId; }
    public void setErrorId(Integer errorId) { this.errorId = errorId; }

    public Integer getSolutionId() { return solutionId; }
    public void setSolutionId(Integer solutionId) { this.solutionId = solutionId; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
