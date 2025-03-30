package com.rest.springapp.controller;

import com.rest.springapp.model.TrainingSession;
import com.rest.springapp.service.TrainingSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sessions")
public class TrainingSessionController {

    @Autowired
    private TrainingSessionService service;

    // Add a new training session
    @PostMapping
    public TrainingSession addSession(@RequestBody TrainingSession session) {
        return service.addSession(session);
    }

    // Get all sessions with pagination & sorting
    @GetMapping
    public Page<TrainingSession> getAllSessions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        return service.getAllSessions(page, size, sortBy);
    }

    // Get session by ID
    @GetMapping("/{id}")
    public Optional<TrainingSession> getSessionById(@PathVariable Long id) {
        return service.getSessionById(id);
    }

    // Delete a training session
    @DeleteMapping("/{id}")
    public String deleteSession(@PathVariable Long id) {
        service.deleteSession(id);
        return "Training session deleted successfully.";
    }

    // Search sessions by title
    @GetMapping("/search")
    public List<TrainingSession> searchSessions(@RequestParam String title) {
        return service.searchSessionsByTitle(title);
    }
}
