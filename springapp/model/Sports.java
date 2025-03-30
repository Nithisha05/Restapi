package com.rest.springapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Sports {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int sportsId;
    private String sportName;
    private String category;
    private String trainingLevel;
    private String coachName;
    private int durationInWeeks;
    private String contactInfo;

    public Sports() {
    }

    public Sports(int sportsId, String sportName, String category, String trainingLevel, String coachName, int durationInWeeks, String contactInfo) {
        this.sportsId = sportsId;
        this.sportName = sportName;
        this.category = category;
        this.trainingLevel = trainingLevel;
        this.coachName = coachName;
        this.durationInWeeks = durationInWeeks;
        this.contactInfo = contactInfo;
    }

    // Getters and Setters
    public int getSportsId() {
        return sportsId;
    }

    public void setSportsId(int sportsId) {
        this.sportsId = sportsId;
    }

    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTrainingLevel() {
        return trainingLevel;
    }

    public void setTrainingLevel(String trainingLevel) {
        this.trainingLevel = trainingLevel;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public int getDurationInWeeks() {
        return durationInWeeks;
    }

    public void setDurationInWeeks(int durationInWeeks) {
        this.durationInWeeks = durationInWeeks;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }
}
