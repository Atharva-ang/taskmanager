package com.atharva.taskmanager.controller;

import com.atharva.taskmanager.entity.Team;
import com.atharva.taskmanager.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teams")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    // POST http://localhost:8080/api/teams
    @PostMapping
    public ResponseEntity<Team> createTeam(@RequestBody Team team, Authentication authentication) {
        // Securely grab the email from the user's JWT token
        String userEmail = authentication.getName();

        Team createdTeam = teamService.createTeam(team, userEmail);
        return ResponseEntity.ok(createdTeam);
    }

    // POST http://localhost:8080/api/teams/{teamId}/users/{userId}
    @PostMapping("/{teamId}/users/{userId}")
    public ResponseEntity<String> addUserToTeam(@PathVariable Long teamId, @PathVariable Long userId) {
        // Triggering the Many-to-Many logic you wrote
        teamService.addUserToTeam(userId, teamId);
        return ResponseEntity.ok("User added to team successfully");
    }
}