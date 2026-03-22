package com.taskmanager.model;

public class Task {

    private Integer id;
    private String title;
    private String description;
    private Boolean completed;
    private String ownerUsername;

    public Task() {}

    public Task(Integer id, String title, String description, Boolean completed, String ownerUsername) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.completed = completed;
        this.ownerUsername = ownerUsername;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Boolean getCompleted() { return completed; }
    public void setCompleted(Boolean completed) { this.completed = completed; }

    public String getOwnerUsername() { return ownerUsername; }
    public void setOwnerUsername(String ownerUsername) { this.ownerUsername = ownerUsername; }
}
