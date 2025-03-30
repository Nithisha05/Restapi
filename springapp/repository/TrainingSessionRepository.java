package com.rest.springapp.repository;

import com.rest.springapp.model.TrainingSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingSessionRepository extends JpaRepository<TrainingSession, Long> {

    // Custom JPQL Query to search sessions by title
    @Query("SELECT ts FROM TrainingSession ts WHERE LOWER(ts.title) LIKE LOWER(CONCAT('%', :title, '%'))")
    List<TrainingSession> findByTitleContainingIgnoreCase(String title);

    // Pagination and sorting support
    @SuppressWarnings("null")
    Page<TrainingSession> findAll(Pageable pageable);
}
