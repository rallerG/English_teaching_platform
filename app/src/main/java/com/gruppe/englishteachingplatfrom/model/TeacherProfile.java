package com.gruppe.englishteachingplatfrom.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    private String email;
    private double rating;
    private int profilePic;
    private String language;
    private int price;

    public TeacherProfile(String name, double rating, int profilePic, String language, int price) {
        this.name = name;
        this.rating = rating;
        this.profilePic = profilePic;
        this.language = language;
        this.price = price;
    }

    public TeacherProfile () {
        this.profilePic = 0;
        this.rating = 0;
        this.price = 0;
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

    public void setName(String name) {
        this.name = name;
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
    public Map<String, Object> toMap() {
        Map<String, Object> mapToReturn = new HashMap<>();
        mapToReturn.put("fullname",this.name);
        mapToReturn.put("mail", this.email);
        return mapToReturn;
    }

    @Override
    public void toObject(String documentId,Map<String, Object> mapOfObject) {
        this.setId(documentId);
        this.setName((String) mapOfObject.get("fullname"));
        this.setEmail((String) mapOfObject.get("mail"));
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(String id) {
        this.id = id;
    }
/*    public int getmPicture() {
        return profilePic;
    }
    public void setmPicture(int profilePic) {
        this.profilePic = profilePic;
    }
    public String getmInfo() {
        return mInfo;
    }*/
}
