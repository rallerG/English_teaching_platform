package com.gruppe.englishteachingplatfrom.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StudentProfile extends DocumentObject{
    private String id;
    private String name;
    private String email;
    private int profilePicture;
    private double rating;
    private static ArrayList<TeacherProfile> pendingProfiles = new ArrayList<TeacherProfile>();
    private static ArrayList<TeacherProfile> favoriteProfiles = new ArrayList<TeacherProfile>();
    private static ArrayList<TeacherProfile> MatchProfiles = new ArrayList<TeacherProfile>();
    private static ArrayList<Payment> activePaymentDummies = new ArrayList<>();
    private static ArrayList<Payment> historyPaymentDummies = new ArrayList<>();


    //Constructor with ID
    public StudentProfile(String id, String name, String email, int profilePicture, double rating) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.profilePicture = profilePicture;
        this.rating = rating;
    }

    public StudentProfile () {
        this.profilePicture = 0;
        this.rating = 0;
    }

    //Constructer without ID for database
    public StudentProfile(String name, String email, int profilePicture, double rating) {
        this.name = name;
        this.email = email;
        this.profilePicture = profilePicture;
        this.rating = rating;
    }

    public ArrayList<Payment> getActivePaymentDummies() {
        return activePaymentDummies;
    }

    public ArrayList<Payment> getHistoryPaymentDummies() {
        return historyPaymentDummies;
    }

    public int getProfilePicture() {
        return profilePicture;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getRating() {
        return rating;
    }

    public static ArrayList<TeacherProfile> getPendingProfiles() {
        return pendingProfiles;
    }

    public static ArrayList<TeacherProfile> getFavoriteProfiles() {
        return favoriteProfiles;
    }

    public static ArrayList<TeacherProfile> getMatchProfiles() {
        return MatchProfiles;
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> mapToReturn = new HashMap<>();
        mapToReturn.put("fullname",this.name);
        mapToReturn.put("mail", this.email);
        return mapToReturn;
    }

    @Override
    public void toObject(String documentId,Map<String, Object> mapOfObject) {
        this.setId(documentId);
        this.setName((String) mapOfObject.get("teacher_fullname"));
        this.setEmail((String) mapOfObject.get("teacher_mail"));
    }

    public String toString() {
        return "Name: " + getName() + "\n Email: " + getEmail();
    }
}
