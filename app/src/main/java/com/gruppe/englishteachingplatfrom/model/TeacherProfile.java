package com.gruppe.englishteachingplatfrom.model;

import java.util.ArrayList;
import java.util.Map;

public class TeacherProfile extends DocumentObject{
    private static ArrayList<StudentProfile> pendingProfiles = new ArrayList<>();
    private static ArrayList<StudentProfile> favoriteProfiles = new ArrayList<>();
    private static ArrayList<StudentProfile> MatchProfiles = new ArrayList<>();
    private static ArrayList<Payment> activePaymentDummies = new ArrayList<>();
    private static ArrayList<Payment> historyPaymentDummies = new ArrayList<>();
    //Needs a picture attribute
    private String id;
    private String name;
    private double rating;
    private int profilePic;
    private String language;
    private int price;

    public TeacherProfile(String id, String name, double rating, int profilePic, String language, int price) {
        this.name = name;
        this.rating = rating;
        this.profilePic = profilePic;
        this.language = language;
        this.price = price;
    }


    public ArrayList<Payment> getActivePaymentDummies() {
        return activePaymentDummies;
    }

    public ArrayList<Payment> getHistoryPaymentDummies() {
        return historyPaymentDummies;
    }

    public ArrayList<StudentProfile> getPendingProfiles() {
        return pendingProfiles;
    }

    public ArrayList<StudentProfile> getFavoriteProfiles() {
        return favoriteProfiles;
    }

    public ArrayList<StudentProfile> getMatchProfiles() {
        return MatchProfiles;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public double getRating() {
        return rating;
    }

    public int getProfilePic() {
        return profilePic;
    }

    public String getLanguage() {
        return language;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public Map<String, String> toMap() {
        return null;
    }
}







