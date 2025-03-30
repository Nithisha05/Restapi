package com.rest.springapp.controller;

import com.rest.springapp.model.Coach;
import com.rest.springapp.service.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/coaches")
public class CoachController {

    @Autowired
    private CoachService service;

    @PostMapping
    public Coach addCoach(@RequestBody Coach coach) {
        return service.addCoach(coach);
    }

    @GetMapping
    public List<Coach> getAllCoaches() {
        return service.getAllCoaches();
    }

    @GetMapping("/{id}")
    public Optional<Coach> getCoachById(@PathVariable Long id) {
        return service.getCoachById(id);
    }

    @PutMapping("/{id}")
    public Coach updateCoach(@PathVariable Long id, @RequestBody Coach updatedCoach) {
        return service.updateCoach(id, updatedCoach);
    }

    @DeleteMapping("/{id}")
    public String deleteCoach(@PathVariable Long id) {
        service.deleteCoach(id);
        return "Coach deleted successfully.";
    }

    @GetMapping("/expertise/{expertise}")
    public List<Coach> getCoachesByExpertise(@PathVariable String expertise) {
        return service.getCoachesByExpertise(expertise);
    }

    @GetMapping("/sorted/{field}")
    public List<Coach> getCoachesSorted(@PathVariable String field) {
        return service.getCoachesSorted(field);
    }

    @GetMapping("/page/{pageNumber}/size/{pageSize}")
    public List<Coach> getCoachesByPage(@PathVariable int pageSize, @PathVariable int pageNumber) {
        return service.getCoachesByPage(pageSize, pageNumber);
    }
}
