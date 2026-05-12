package com.example.taskmanager.model;

public class Task {
    private Integer id;
    private String title;
    private Boolean completed;

    public Task(Integer id, String title, Boolean completed) {
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Boolean isCompleted() {
        return completed;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

}
