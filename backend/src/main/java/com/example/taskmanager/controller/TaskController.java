package com.example.taskmanager.controller;
import com.example.taskmanager.model.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
public class TaskController {
    private List<Task> tasks = new ArrayList<>();
    private int idCounter = 1;

    public TaskController() {
    }
    
    @GetMapping("/tasks")
    public List<Task> getTasks() {
        return tasks;
    }

    @PostMapping("/tasks")
    public Task createTask(@RequestBody Task task) {
        task.setId(idCounter++);
        tasks.add(task);

        return task;
    }

    @PutMapping("/tasks/{id}")
    public Task updateTask(@PathVariable Integer id, @RequestBody Task updatedTask) {
        Task existing = findTaskById(id);
        if (existing == null) {
            throw new RuntimeException("Task not found");
        }
        if (updatedTask.getTitle() != null) {
            existing.setTitle(updatedTask.getTitle());
        }
        if (updatedTask.isCompleted() != null) {
            existing.setCompleted(updatedTask.isCompleted());
        }
        return existing;
    }

    @DeleteMapping("/tasks/{id}")
    public Task deleteTask(@PathVariable Integer id){
        Task taskToRemove = findTaskById(id);
        if (taskToRemove == null) {
            throw new RuntimeException("Task not found");
        }
        tasks.remove(taskToRemove);
        return taskToRemove;
    }

    private Task findTaskById(Integer id) {
        for (int i = 0; i < tasks.size(); i++) {
            if (Objects.equals(tasks.get(i).getId(), id)){
                return tasks.get(i);
            }
        }
        return null;
    }

}
