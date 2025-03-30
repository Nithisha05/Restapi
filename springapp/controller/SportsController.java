package com.rest.springapp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rest.springapp.model.Sports;
import com.rest.springapp.service.SportsService;

@RestController
@RequestMapping("/api/sports")
public class SportsController {
    @Autowired
    private SportsService sportsService;

    // Add a new sport
    @SuppressWarnings("null")
    @PostMapping
    public ResponseEntity<Sports> addSport(@RequestBody Sports sport) {
        try {
            sportsService.addSport(sport);
            return new ResponseEntity<>(sport, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get all sports
    @SuppressWarnings("null")
    @GetMapping
    public ResponseEntity<List<Sports>> getAllSports() {
        try {
            return new ResponseEntity<>(sportsService.getAllSports(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get sports by category
    @SuppressWarnings("null")
    @GetMapping("/byCategory")
    public ResponseEntity<List<Sports>> getSportsByCategory(@RequestParam String category) {
        try {
            return new ResponseEntity<>(sportsService.getSportsByCategory(category), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get paginated sports
    // Sample URL: GET http://localhost:8080/api/sports/paged?page=0&size=5
    @SuppressWarnings("null")
    @GetMapping("/paged")
    public ResponseEntity<Page<Sports>> getSportsPaged(@RequestParam int page, @RequestParam int size) {
        try {
            return new ResponseEntity<>(sportsService.getSportsPaged(page, size), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get paginated and sorted sports
    // Sample URL: GET http://localhost:8080/api/sports/pagedAndSorted?page=0&size=5&sortBy=sportName
    @SuppressWarnings("null")
    @GetMapping("/pagedAndSorted")
    public ResponseEntity<Page<Sports>> getSportsPagedAndSorted(@RequestParam int page, @RequestParam int size, @RequestParam String sortBy) {
        try {
            return new ResponseEntity<>(sportsService.getSportsPagedAndSorted(page, size, sortBy), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get count of sports
    // Sample URL: GET http://localhost:8080/api/sports/count
    @SuppressWarnings("null")
    @GetMapping("/count")
    public ResponseEntity<Long> getSportsCount() {
        try {
            return new ResponseEntity<>(sportsService.countSports(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get sports with JPQL filtering, pagination, and sorting
    // Sample URL: POST http://localhost:8080/api/sports/filter
    // Request Body (JSON):
    // {
    //    "sportName": "Soccer",
    //    "category": "Team",
    //    "trainingLevel": "Intermediate",
    //    "page": 0,
    //    "size": 5,
    //    "sortBy": "sportName"
    // }
    @SuppressWarnings("null")
    @PostMapping("/filter")
    public ResponseEntity<Page<Sports>> getSportsFiltered(@RequestBody Map<String, Object> filters) {
        try {
            String sportName = (String) filters.getOrDefault("sportName", null);
            String category = (String) filters.getOrDefault("category", null);
            String trainingLevel = (String) filters.getOrDefault("trainingLevel", null);
            int page = (int) filters.getOrDefault("page", 0);
            int size = (int) filters.getOrDefault("size", 5);
            String sortBy = (String) filters.getOrDefault("sortBy", "sportsId");

            Page<Sports> result = sportsService.getSportsFiltered(sportName, category, trainingLevel, page, size, sortBy);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update a sport (PUT request)
    @SuppressWarnings("null")
    @PutMapping("/{id}")
    public ResponseEntity<Sports> updateSport(@PathVariable int id, @RequestBody Sports updatedSport) {
        try {
            Sports sport = sportsService.updateSport(id, updatedSport);
            if (sport != null) {
                return new ResponseEntity<>(sport, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
