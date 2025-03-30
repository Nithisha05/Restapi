package com.rest.springapp.repository;

import com.rest.springapp.model.Equipment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {

    // Find equipment by type
    @Query("SELECT e FROM Equipment e WHERE e.type = :type")
    List<Equipment> findByType(@Param("type") String type);

    // Find equipment with quantity greater than a given value
    @Query("SELECT e FROM Equipment e WHERE e.quantity > :minQuantity")
    List<Equipment> findByMinimumQuantity(@Param("minQuantity") int minQuantity);

    // Find equipment with pagination and sorting
    @Query("SELECT e FROM Equipment e")
    List<Equipment> findAllEquipment(Pageable pageable);
}
