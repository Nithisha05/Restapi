package com.rest.springapp.service;

import com.rest.springapp.model.TrainingSession;
import com.rest.springapp.repository.TrainingSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainingSessionService {

    @Autowired
    private TrainingSessionRepository repository;

    // Get all sessions with pagination & sorting
    public Page<TrainingSession> getAllSessions(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return repository.findAll(pageable);
    }

    // Get session by ID
    public Optional<TrainingSession> getSessionById(Long id) {
        return repository.findById(id);
    }

    // Add a new session
    public TrainingSession addSession(TrainingSession session) {
        return repository.save(session);
    }

    // Delete a session by ID
    public void deleteSession(Long id) {
        repository.deleteById(id);
    }

    // Search sessions by title (JPQL Query)
    public List<TrainingSession> searchSessionsByTitle(String title) {
        return repository.findByTitleContainingIgnoreCase(title);
    }
}
