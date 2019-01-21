package com.gruppe.englishteachingplatfrom.model;

import com.gruppe.englishteachingplatfrom.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StudentProfile extends DocumentObject{
    private String id;
    private String name;
    private String email;
    private String password;
    private int profilePicture;
    private static ArrayList<TeacherProfile> pendingProfiles = new ArrayList<TeacherProfile>();
    private static ArrayList<TeacherProfile> favoriteProfiles = new ArrayList<TeacherProfile>();
    private static ArrayList<TeacherProfile> MatchProfiles = new ArrayList<TeacherProfile>();
    private static ArrayList<Payment> activePaymentDummies = new ArrayList<>();
    private static ArrayList<Payment> historyPaymentDummies = new ArrayList<>();


    //Constructor with ID
    public StudentProfile(String id, String name, String email, int profilePicture) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.profilePicture = profilePicture;
        this.password = "123";
    }

    public StudentProfile () {
        this.profilePicture = R.drawable.unknown;
        this.password = "123";
    }

    //Constructer without ID for database
    public StudentProfile(String name, String email, int profilePicture) {
        this.name = name;
        this.email = email;
        this.profilePicture = profilePicture;
        this.password = "123";
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

    public ArrayList<TeacherProfile> getPendingProfiles() {
        return pendingProfiles;
    }

    public ArrayList<TeacherProfile> getFavoriteProfiles() {
        return favoriteProfiles;
    }

    public ArrayList<TeacherProfile> getMatchProfiles() {
        return MatchProfiles;
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> mapToReturn = new HashMap<>();
        mapToReturn.put("fullname",this.name);
        mapToReturn.put("mail", this.email);
        mapToReturn.put("password",this.password);
        mapToReturn.put("profilePic", this.profilePicture);
        return mapToReturn;
    }

    @Override
    public void toObject(String documentId,Map<String, Object> mapOfObject) {
        if(mapOfObject.containsKey("student_id")) {
            this.setId((String) mapOfObject.get("student_id"));
        }
        else {
            this.setId(documentId);
            this.setName((String) mapOfObject.get("student_fullname"));
            this.setEmail((String) mapOfObject.get("student_mail"));
            this.setPassword((String) mapOfObject.get("student_password"));
            this.setProfilePicture(Math.toIntExact((long) mapOfObject.get("student_profilePic")));
        }
    }

    public String toString() {
        return "Name: " + getName() + "\n Email: " + getEmail();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setProfilePicture(int profilePicture) {
        this.profilePicture = profilePicture;
    }
}
