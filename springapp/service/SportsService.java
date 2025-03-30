package com.rest.springapp.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.rest.springapp.model.Sports;
import com.rest.springapp.repository.SportsRepo;

@Service
public class SportsService {
    @Autowired
    private SportsRepo sportsRepo;

    // Add a new sport
    public void addSport(Sports sport) {
        sportsRepo.save(sport);
    }

    // Get all sports
    public List<Sports> getAllSports() {
        return sportsRepo.count() == 0 ? Collections.emptyList() : sportsRepo.getAllSports();
    }

    // Get sports by category
    public List<Sports> getSportsByCategory(String category) {
        return sportsRepo.count() == 0 ? Collections.emptyList() : sportsRepo.getByCategory(category);
    }

    // Update a sport by ID
    public Sports updateSport(int id, Sports updatedSport) {
        Optional<Sports> existingSport = sportsRepo.findById(id);
        if (existingSport.isPresent()) {
            updatedSport.setSportsId(id); // Ensure updating the same sport
            return sportsRepo.save(updatedSport);
        }
        return null;
    }

    // Get paginated sports
    public Page<Sports> getSportsPaged(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return sportsRepo.findAll(pageable);
    }

    // Get paginated and sorted sports
    public Page<Sports> getSportsPagedAndSorted(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, sortBy));
        return sportsRepo.findAll(pageable);
    }

    // Get count of sports
    public long countSports() {
        return sportsRepo.count();
    }

    // Get sports with JPQL filtering, pagination, and sorting
    public Page<Sports> getSportsFiltered(String sportName, String category, String trainingLevel, int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, sortBy));
        return sportsRepo.findSportsByFilters(sportName, category, trainingLevel, pageable);
    }
}
