package com.example.taskmanager.controller;
import com.example.taskmanager.dto.TaskRequestDto;
import com.example.taskmanager.dto.TaskResponseDto;
import com.example.taskmanager.mapper.TaskMapper;
import com.example.taskmanager.model.Task;
import com.example.taskmanager.repository.TaskRepository;
import com.example.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    
    @GetMapping("/tasks")
    public List<TaskResponseDto> getTasks() {
        return taskService.getAllTasks();
    }

    @PostMapping("/tasks")
    public TaskResponseDto createTask(@RequestBody TaskRequestDto dto) {
        return taskService.createTask(dto);
    }

    @PutMapping("/tasks/{id}")
    public TaskResponseDto updateTask(@PathVariable Integer id, @RequestBody TaskRequestDto dto) {
        return taskService.updateTask(id, dto);
    }

    @DeleteMapping("/tasks/{id}")
    public void deleteTask(@PathVariable Integer id){
        taskService.deleteTask(id);
    }

}
