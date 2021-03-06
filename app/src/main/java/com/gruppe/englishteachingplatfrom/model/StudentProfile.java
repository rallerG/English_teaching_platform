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
//        switch (this.id) {
//            case "1": this.profilePicture = R.drawable.student_1;
//                break;
//            case "2": this.profilePicture = R.drawable.student_2;
//                break;
//            case "3": this.profilePicture = R.drawable.student_3;
//                break;
//                default: this.profilePicture = R.drawable.unknown;
//                break;
//        }

//        this.profilePicture = R.drawable.unknown;
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


    public void setProfilePictures() {
        switch (this.id) {
            case "1": this.profilePicture = R.drawable.student_2;
                break;
            case "2": this.profilePicture = R.drawable.student_1;
                break;
            case "3": this.profilePicture = R.drawable.student_3;
                break;
            case "6mNdoUsjxY3kOy4UzXRE": this.profilePicture = R.drawable.student_4;
                break;
            case "9tjJYykAYic3Kdclaoch": this.profilePicture = R.drawable.student_5;
                break;
            case "DcCadrswTacgCOHMuOxN": this.profilePicture = R.drawable.student_6;
                break;
            case "LTELj1lEb56xUdzeW28I": this.profilePicture = R.drawable.student_7;
                break;
            case "SBbSFiI9BSKJHTI0kOXj": this.profilePicture = R.drawable.student_8;
                break;
            case "lnipcGhVIQbCpD4GF1CH": this.profilePicture = R.drawable.student_9;
                break;
            case "vQbuEBdR5dR6uSpuSECk": this.profilePicture = R.drawable.student_10;
                break;
            case "vfaKJzNUM3QYzahhqbNv": this.profilePicture = R.drawable.student_11;
                break;
            case "1eWuxAJ43lustIJMfT8D": this.profilePicture = R.drawable.student_12;
                break;
            default: this.profilePicture = R.drawable.unknown;
                break;
        }
    }

    public String getFirstName() {
        String name = getName();
        String[] parts = name.split(" ");
        return parts[0];
    }

}
