package com.atharva.taskmanager.service;

import com.atharva.taskmanager.entity.Team;
import com.atharva.taskmanager.entity.User;
import com.atharva.taskmanager.repository.TeamRepository;
import com.atharva.taskmanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;
    private final UserRepository userRepository;

    // 1. Create a new team AND add the creator to it securely
    public Team createTeam(Team team, String userEmail) {
        User creator = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Add the creator to the team's user list
        team.getUsers().add(creator);

        return teamRepository.save(team);
    }

    // 2. Add a user to a team (Your Many-to-Many logic)
    public void addUserToTeam(Long userId, Long teamId) {
        Optional<User> userOpt = userRepository.findById(userId);
        Optional<Team> teamOpt = teamRepository.findById(teamId);

        if (userOpt.isPresent() && teamOpt.isPresent()) {
            User user = userOpt.get();
            Team team = teamOpt.get();

            user.getTeams().add(team);
            userRepository.save(user);
        }
    }
}