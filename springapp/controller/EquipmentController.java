package com.rest.springapp.controller;

import com.rest.springapp.model.Equipment;
import com.rest.springapp.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/equipment")
public class EquipmentController {

    @Autowired
    private EquipmentService service;

    // Add new equipment
    @PostMapping
    public Equipment addEquipment(@RequestBody Equipment equipment) {
        return service.addEquipment(equipment);
    }

    // Get all equipment
    @GetMapping
    public List<Equipment> getAllEquipment() {
        return service.getAllEquipment();
    }

    // Get equipment by ID
    @GetMapping("/{id}")
    public Optional<Equipment> getEquipmentById(@PathVariable Long id) {
        return service.getEquipmentById(id);
    }

    // Update equipment
    @PutMapping("/{id}")
    public Equipment updateEquipment(@PathVariable Long id, @RequestBody Equipment updatedEquipment) {
        return service.updateEquipment(id, updatedEquipment);
    }

    // Delete equipment
    @DeleteMapping("/{id}")
    public String deleteEquipment(@PathVariable Long id) {
        service.deleteEquipment(id);
        return "Equipment deleted successfully.";
    }

    // Get sorted equipment
    @GetMapping("/sorted/{field}")
    public List<Equipment> getEquipmentSorted(@PathVariable String field) {
        return service.getEquipmentSorted(field);
    }

    // Get paginated equipment
    @GetMapping("/page/{pageNumber}/size/{pageSize}")
    public List<Equipment> getEquipmentByPage(@PathVariable int pageSize, @PathVariable int pageNumber) {
        return service.getEquipmentByPage(pageSize, pageNumber);
    }

    // Get paginated and sorted equipment
    @GetMapping("/page/{pageNumber}/size/{pageSize}/sorted/{field}")
    public List<Equipment> getEquipmentByPageAndSort(
            @PathVariable int pageSize, 
            @PathVariable int pageNumber, 
            @PathVariable String field) {
        return service.getEquipmentByPageAndSort(pageSize, pageNumber, field);
    }
}
