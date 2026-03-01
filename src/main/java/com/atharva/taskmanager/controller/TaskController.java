package com.atharva.taskmanager.controller;

import com.atharva.taskmanager.entity.Task;
import com.atharva.taskmanager.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    // POST http://localhost:8080/api/tasks
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task, Authentication authentication) {
        // authentication.getName() automatically grabs the email we extracted from the JWT
        String userEmail = authentication.getName();

        Task createdTask = taskService.createTask(task, userEmail);
        return ResponseEntity.ok(createdTask);
    }

    // GET http://localhost:8080/api/tasks
    @GetMapping
    public ResponseEntity<List<Task>> getUserTasks(Authentication authentication) {
        String userEmail = authentication.getName();

        List<Task> tasks = taskService.getTasksByUser(userEmail);
        return ResponseEntity.ok(tasks);
    }
}