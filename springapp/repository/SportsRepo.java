package com.rest.springapp.repository;

import com.rest.springapp.model.Sports;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface SportsRepo extends JpaRepository<Sports, Integer> {
    
    @Query("SELECT s FROM Sports s")
    List<Sports> getAllSports();

    @Query("SELECT s FROM Sports s WHERE s.category = ?1")
    List<Sports> getByCategory(String category);

    // JPQL filtering with optional parameters (using CONCAT for LIKE expressions)
    @Query("SELECT s FROM Sports s WHERE " +
           "(:sportName IS NULL OR s.sportName LIKE CONCAT('%', :sportName, '%')) AND " +
           "(:category IS NULL OR s.category LIKE CONCAT('%', :category, '%')) AND " +
           "(:trainingLevel IS NULL OR s.trainingLevel LIKE CONCAT('%', :trainingLevel, '%'))")
    Page<Sports> findSportsByFilters(@Param("sportName") String sportName,
                                     @Param("category") String category,
                                     @Param("trainingLevel") String trainingLevel,
                                     Pageable pageable);
}
