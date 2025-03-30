package com.rest.springapp.controller;

import com.rest.springapp.model.Athlete;
import com.rest.springapp.service.AthleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/athletes")
public class AthleteController {
    
    @Autowired
    private AthleteService service;

    // 1️⃣ Add a new athlete
    @PostMapping
    public ResponseEntity<Athlete> addAthlete(@RequestBody Athlete athlete) {
        Athlete createdAthlete = service.addAthlete(athlete);
        return ResponseEntity.ok(createdAthlete);
    }

    // 2️⃣ Get all athletes
    @GetMapping
    public ResponseEntity<List<Athlete>> getAllAthletes() {
        return ResponseEntity.ok(service.getAllAthletes());
    }

    // 3️⃣ Get athlete by ID
    @GetMapping("/{id}")
    public ResponseEntity<Athlete> getAthleteById(@PathVariable int id) {
        Optional<Athlete> athlete = service.getAthleteById(id);
        return athlete.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 4️⃣ Update athlete details
    @PutMapping("/{id}")
    public ResponseEntity<Athlete> updateAthlete(@PathVariable int id, @RequestBody Athlete updatedAthlete) {
        Athlete athlete = service.updateAthlete(id, updatedAthlete);
        return ResponseEntity.ok(athlete);
    }

    // 5️⃣ Delete an athlete
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAthlete(@PathVariable int id) {
        return ResponseEntity.ok(service.deleteAthlete(id));
    }

    // 6️⃣ Get sorted athletes
    @GetMapping("/sorted/{field}")
    public ResponseEntity<List<Athlete>> getAllAthletesSorted(@PathVariable String field) {
        return ResponseEntity.ok(service.getAllAthletesSorted(field));
    }

    // 7️⃣ Get paginated athletes
    @GetMapping("/page/{pageNumber}/size/{pageSize}")
    public ResponseEntity<List<Athlete>> getAthletesByPage(@PathVariable int pageSize, @PathVariable int pageNumber) {
        return ResponseEntity.ok(service.getAthletesByPage(pageSize, pageNumber));
    }

    // 8️⃣ Get paginated and sorted athletes
    @GetMapping("/page/{pageNumber}/size/{pageSize}/sorted/{field}")
    public ResponseEntity<List<Athlete>> getAthletesByPageAndSort(
            @PathVariable int pageSize, 
            @PathVariable int pageNumber, 
            @PathVariable String field) {
        return ResponseEntity.ok(service.getAthletesByPageAndSort(pageSize, pageNumber, field));
    }

    // 9️⃣ Get athletes with filters, pagination, and sorting using JPQL
    @PostMapping("/filter")
    public ResponseEntity<Page<Athlete>> getAthletesFiltered(@RequestBody Map<String, Object> filters) {
        String firstName = (String) filters.getOrDefault("firstName", null);
        String lastName = (String) filters.getOrDefault("lastName", null);
        String sport = (String) filters.getOrDefault("sport", null);
        String trainingLevel = (String) filters.getOrDefault("trainingLevel", null);
        int pageSize = (int) filters.getOrDefault("pageSize", 5);
        int pageNumber = (int) filters.getOrDefault("pageNumber", 0);
        String sortBy = (String) filters.getOrDefault("sortBy", "athleteId");

        return ResponseEntity.ok(service.getAthletesFiltered(firstName, lastName, sport, trainingLevel, pageSize, pageNumber, sortBy));
    }
}
