package com.atharva.taskmanager.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String description; // Allowing a longer text limit for descriptions

    @Column(nullable = false)
    private String status; // We will use strings like "OPEN", "IN_PROGRESS", "COMPLETED"

    private LocalDateTime dueDate;

    // A User can have many tasks, but this specific task belongs to ONE user.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}