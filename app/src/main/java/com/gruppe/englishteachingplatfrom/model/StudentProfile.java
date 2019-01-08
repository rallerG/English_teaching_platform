package com.gruppe.englishteachingplatfrom.model;

import java.util.ArrayList;

public class StudentProfile {
    private String id;
    private String Name;
    private String email;
    private int profilePicture;
    private double rating;
    private static ArrayList<TeacherProfile> pendingProfiles = new ArrayList<TeacherProfile>();
    private static ArrayList<TeacherProfile> favoriteProfiles = new ArrayList<TeacherProfile>();
    private static ArrayList<TeacherProfile> MatchProfiles = new ArrayList<TeacherProfile>();


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
