package com.atharva.taskmanager.service;

import com.atharva.taskmanager.entity.Task;
import com.atharva.taskmanager.entity.User;
import com.atharva.taskmanager.repository.TaskRepository;
import com.atharva.taskmanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    // 1. Create a new task and link it to the logged-in user
    public Task createTask(Task task, String userEmail) {
        // Find the user from the database using the email extracted from the JWT
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found")); // We'll handle this properly in the Refinement phase!

        // Attach the user to the task
        task.setUser(user);

        // Save to PostgreSQL
        return taskRepository.save(task);
    }

    // 2. Fetch only the tasks belonging to a specific user
    public List<Task> getTasksByUser(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return taskRepository.findByUserId(user.getId());
    }
}