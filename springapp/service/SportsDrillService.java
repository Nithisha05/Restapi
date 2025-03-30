package com.rest.springapp.service;

import com.rest.springapp.model.SportsDrill;
import com.rest.springapp.repository.SportsDrillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SportsDrillService {

    @Autowired
    private SportsDrillRepository repository;

    // Get all drills with pagination & sorting
    public Page<SportsDrill> getAllDrills(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return repository.findAll(pageable);
    }

    // Get drill by ID
    public Optional<SportsDrill> getDrillById(Long id) {
        return repository.findById(id);
    }

    // Add a new drill
    public SportsDrill addDrill(SportsDrill drill) {
        return repository.save(drill);
    }

    // Update an existing drill
    public SportsDrill updateDrill(Long id, SportsDrill drillDetails) {
        return repository.findById(id).map(drill -> {
            drill.setName(drillDetails.getName());
            drill.setDescription(drillDetails.getDescription());
            drill.setDuration(drillDetails.getDuration());
            return repository.save(drill);
        }).orElseThrow(() -> new RuntimeException("Drill not found with ID: " + id));
    }

    // Delete a drill by ID
    public void deleteDrill(Long id) {
        repository.deleteById(id);
    }

    // Search drills by name (JPQL Query)
    public List<SportsDrill> searchDrillsByName(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }
}
