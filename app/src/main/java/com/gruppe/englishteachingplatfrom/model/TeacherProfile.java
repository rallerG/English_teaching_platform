package com.gruppe.englishteachingplatfrom.model;

import java.util.ArrayList;

public class TeacherProfile {
    private static ArrayList<StudentProfile> pendingProfiles = new ArrayList<>();
    private static ArrayList<StudentProfile> favoriteProfiles = new ArrayList<>();
    private static ArrayList<StudentProfile> MatchProfiles = new ArrayList<>();
    //Needs a picture attribute
    private String id;
    private String name;



    private String rating;
    private int profilePic;
    private String language;
    private int price;

    public TeacherProfile(String id, String name, String rating, int profilePic, String language, int price) {
        this.name = name;
        this.rating = rating;
        this.profilePic = profilePic;
        this.language = language;
        this.price = price;
    }




    public static ArrayList<StudentProfile> getPendingProfiles() {
        return pendingProfiles;
    }

    public static ArrayList<StudentProfile> getFavoriteProfiles() {
        return favoriteProfiles;
    }

    public static ArrayList<StudentProfile> getMatchProfiles() {
        return MatchProfiles;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getRating() {
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
}







