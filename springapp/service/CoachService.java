package com.rest.springapp.service;

import com.rest.springapp.model.Coach;
import com.rest.springapp.repository.CoachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoachService {

    @Autowired
    private CoachRepository repository;

    public Coach addCoach(Coach coach) {
        return repository.save(coach);
    }

    public List<Coach> getAllCoaches() {
        return repository.findAll();
    }

    public Optional<Coach> getCoachById(Long id) {
        return repository.findById(id);
    }

    public Coach updateCoach(Long id, Coach updatedCoach) {
        return repository.findById(id).map(coach -> {
            coach.setName(updatedCoach.getName());
            coach.setExpertise(updatedCoach.getExpertise());
            coach.setExperience(updatedCoach.getExperience());
            return repository.save(coach);
        }).orElseThrow(() -> new RuntimeException("Coach not found with ID: " + id));
    }

    public void deleteCoach(Long id) {
        repository.deleteById(id);
    }

    public List<Coach> getCoachesByExpertise(String expertise) {
        return repository.findByExpertise(expertise);
    }

    public List<Coach> getCoachesSorted(String field) {
        return repository.findAll(Sort.by(Sort.Direction.ASC, field));
    }

    public List<Coach> getCoachesByPage(int pageSize, int pageNumber) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        return repository.findAll(page).getContent();
    }
}
