package com.rest.springapp.controller;

import com.rest.springapp.model.SportsDrill;
import com.rest.springapp.service.SportsDrillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/drills")
public class SportsDrillController {

    @Autowired
    private SportsDrillService service;

    // Get all drills with pagination & sorting
    @GetMapping
    public Page<SportsDrill> getAllDrills(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        return service.getAllDrills(page, size, sortBy);
    }

    // Get drill by ID
    @GetMapping("/{id}")
    public Optional<SportsDrill> getDrillById(@PathVariable Long id) {
        return service.getDrillById(id);
    }

    // Create a new drill
    @PostMapping
    public SportsDrill createDrill(@RequestBody SportsDrill drill) {
        return service.addDrill(drill);
    }

    // Update an existing drill
    @PutMapping("/{id}")
    public SportsDrill updateDrill(@PathVariable Long id, @RequestBody SportsDrill drill) {
        return service.updateDrill(id, drill);
    }

    // Delete a drill by ID
    @DeleteMapping("/{id}")
    public String deleteDrill(@PathVariable Long id) {
        service.deleteDrill(id);
        return "Drill deleted successfully";
    }

    // Search drills by name
    @GetMapping("/search")
    public List<SportsDrill> searchDrills(@RequestParam String name) {
        return service.searchDrillsByName(name);
    }
}
