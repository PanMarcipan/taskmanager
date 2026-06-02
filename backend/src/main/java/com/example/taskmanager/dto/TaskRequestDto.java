package com.example.taskmanager.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TaskRequestDto {
    @JsonProperty("title")
    private final String title;

    @JsonProperty("is_completed")
    private final Boolean completed;

    public TaskRequestDto(String title, Boolean completed){
        this.title = title;
        this.completed = completed;
    }

    public String getTitle() {
        return title;
    }

    public Boolean getCompleted() {
        return completed;
    }
}
