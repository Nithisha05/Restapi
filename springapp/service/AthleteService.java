package com.rest.springapp.service;

import com.rest.springapp.model.Athlete;
import com.rest.springapp.repository.AthleteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AthleteService {
    
    @Autowired
    private AthleteRepo repo;

    // Add a new athlete
    public Athlete addAthlete(Athlete athlete) {
        return repo.save(athlete);
    }

    // Get all athletes
    public List<Athlete> getAllAthletes() {
        return repo.findAll();
    }

    // Get athlete by ID
    public Optional<Athlete> getAthleteById(int id) {
        return repo.findById(id);
    }

    // Update athlete details
    public Athlete updateAthlete(int id, Athlete updatedAthlete) {
        if (repo.existsById(id)) {
            updatedAthlete.setAthleteId(id);
            return repo.save(updatedAthlete);
        }
        return null; // Or throw an exception
    }

    // Delete an athlete
    public String deleteAthlete(int id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return "Athlete with ID " + id + " deleted successfully.";
        }
        return "Athlete not found.";
    }

    // Get sorted athletes
    public List<Athlete> getAllAthletesSorted(String field) {
        return repo.findAll(Sort.by(Sort.Direction.ASC, field));
    }

    // Get paginated athletes
    public List<Athlete> getAthletesByPage(int pageSize, int pageNumber) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        return repo.findAll(page).getContent();
    }

    // Get paginated and sorted athletes
    public List<Athlete> getAthletesByPageAndSort(int pageSize, int pageNumber, String field) {
        return repo.findAll(PageRequest.of(pageNumber, pageSize).withSort(Sort.by(Sort.Direction.ASC, field)))
                .getContent();
    }

    // Get athletes with filters, pagination, and sorting using JPQL
    public Page<Athlete> getAthletesFiltered(
            String firstName, String lastName, String sport, String trainingLevel,
            int pageSize, int pageNumber, String sortBy) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, sortBy));
        return repo.findAthletesWithFilters(firstName, lastName, sport, trainingLevel, pageable);
    }

    public static List<Athlete> findAthletesByFilter(String firstName, String lastName, String sport,
            String trainingLevel, int pageNumber, int pageSize, String sortBy) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAthletesByFilter'");
    }

    public static Object countAthletes() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'countAthletes'");
    }
}
