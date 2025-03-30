package com.rest.springapp.repository;

import com.rest.springapp.model.Coach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoachRepository extends JpaRepository<Coach, Long> {

    // Custom JPQL Query to find coaches by expertise
    @Query("SELECT c FROM Coach c WHERE c.expertise = :expertise")
    List<Coach> findByExpertise(@Param("expertise") String expertise);
}
