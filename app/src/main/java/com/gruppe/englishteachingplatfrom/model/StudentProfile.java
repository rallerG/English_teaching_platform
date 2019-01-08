package com.gruppe.englishteachingplatfrom.model;

public class StudentProfile {
    private String id;
    private String Name;
    private String email;
    private int profilePicture;
    private double rating;

    //Constructer with ID
    public StudentProfile(String id, String name, String email, int profilePicture, double rating) {
        this.id = id;
        Name = name;
        this.email = email;
        this.profilePicture = profilePicture;
        this.rating = rating;
    }

    //Constructer without ID for database
    public StudentProfile(String name, String email, int profilePicture, double rating) {
        Name = name;
        this.email = email;
        this.profilePicture = profilePicture;
        this.rating = rating;
    }
}
