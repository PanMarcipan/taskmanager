package com.example.taskmanager.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TaskResponseDto {
    @JsonProperty("id")
    private final Integer id;

    @JsonProperty("title")
    private final String title;

    @JsonProperty("is_completed")
    private final Boolean completed;

    public TaskResponseDto(Integer id, String title, Boolean completed){
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    public String getTitle() {
        return title;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public Integer getId() {
        return id;
    }
}
