package com.rest.springapp.repository;

import com.rest.springapp.model.SportsDrill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SportsDrillRepository extends JpaRepository<SportsDrill, Long> {

    // Custom JPQL Query to search drills by name
    @Query("SELECT d FROM SportsDrill d WHERE LOWER(d.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<SportsDrill> findByNameContainingIgnoreCase(String name);

    // Pagination and sorting support
    @SuppressWarnings("null")
    Page<SportsDrill> findAll(Pageable pageable);
}
