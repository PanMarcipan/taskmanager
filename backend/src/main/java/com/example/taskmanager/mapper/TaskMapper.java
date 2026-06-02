package com.example.taskmanager.mapper;

import com.example.taskmanager.dto.TaskRequestDto;
import com.example.taskmanager.dto.TaskResponseDto;
import com.example.taskmanager.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskMapper {

    public static Task toEntity(TaskRequestDto dto) {
        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setCompleted(dto.getCompleted());
        return task;
    }

    public static TaskResponseDto toDto(Task task) {
        return new TaskResponseDto(
                task.getId(),
                task.getTitle(),
                task.getCompleted()
        );
    }

    public static List<TaskResponseDto> toDtoList(List<Task> tasks) {
        List<TaskResponseDto> dtos = new ArrayList<TaskResponseDto>();
        for (Task task : tasks) {
            dtos.add(toDto(task));
        }
        return dtos;
    }

    public static void updateEntity(Task task, TaskRequestDto dto) {
        if (dto.getTitle() != null) {
            task.setTitle(dto.getTitle());
        }
        if (dto.getCompleted() != null) {
            task.setCompleted(dto.getCompleted());
        }
    }
}
