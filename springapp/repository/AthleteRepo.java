package com.rest.springapp.repository;

import com.rest.springapp.model.Athlete;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AthleteRepo extends JpaRepository<Athlete, Integer> {

       @Query("SELECT a FROM Athlete a WHERE " +
       "(COALESCE(:firstName, '') = '' OR a.firstName LIKE %:firstName%) AND " +
       "(COALESCE(:lastName, '') = '' OR a.lastName LIKE %:lastName%) AND " +
       "(COALESCE(:sport, '') = '' OR a.sport LIKE %:sport%) AND " +
       "(COALESCE(:trainingLevel, '') = '' OR a.trainingLevel = :trainingLevel)")
Page<Athlete> findAthletesWithFilters(
        @Param("firstName") String firstName,
        @Param("lastName") String lastName,
        @Param("sport") String sport,
        @Param("trainingLevel") String trainingLevel,
        Pageable pageable);
        @Query("SELECT COUNT(a) FROM Athlete a WHERE a.sport = :sport")
long countBySport(@Param("sport") String sport);


}
