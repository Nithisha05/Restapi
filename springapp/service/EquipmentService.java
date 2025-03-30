package com.rest.springapp.service;

import com.rest.springapp.model.Equipment;
import com.rest.springapp.repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipmentService {

    @Autowired
    private EquipmentRepository repository;

    // Add new equipment
    public Equipment addEquipment(Equipment equipment) {
        return repository.save(equipment);
    }

    // Get all equipment
    public List<Equipment> getAllEquipment() {
        return repository.findAll();
    }

    // Get equipment by ID
    public Optional<Equipment> getEquipmentById(Long id) {
        return repository.findById(id);
    }

    // Update equipment details
    public Equipment updateEquipment(Long id, Equipment updatedEquipment) {
        return repository.findById(id).map(equipment -> {
            equipment.setName(updatedEquipment.getName());
            equipment.setType(updatedEquipment.getType());
            equipment.setQuantity(updatedEquipment.getQuantity());
            return repository.save(equipment);
        }).orElseThrow(() -> new RuntimeException("Equipment not found with ID: " + id));
    }

    // Delete equipment by ID
    public void deleteEquipment(Long id) {
        repository.deleteById(id);
    }

    // Get sorted list of equipment
    public List<Equipment> getEquipmentSorted(String field) {
        return repository.findAll(Sort.by(Sort.Direction.ASC, field));
    }

    // Get paginated list of equipment
    public List<Equipment> getEquipmentByPage(int pageSize, int pageNumber) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        return repository.findAll(page).getContent();
    }

    // Get paginated and sorted equipment
    public List<Equipment> getEquipmentByPageAndSort(int pageSize, int pageNumber, String field) {
        return repository.findAll(PageRequest.of(pageNumber, pageSize)
                .withSort(Sort.by(Sort.Direction.ASC, field)))
                .getContent();
    }
}
