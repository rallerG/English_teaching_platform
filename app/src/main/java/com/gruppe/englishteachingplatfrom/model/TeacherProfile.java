package com.gruppe.englishteachingplatfrom.model;

import com.gruppe.englishteachingplatfrom.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TeacherProfile extends DocumentObject{
    private static ArrayList<StudentProfile> pendingProfiles = new ArrayList<>();
    private static ArrayList<Review> reviewList = new ArrayList<>();
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
    private String password;
    private String description;

    public TeacherProfile(String name, double rating, int profilePic, String language, int price) {
        this.name = name;
        this.rating = rating;
        this.profilePic = profilePic;
        this.language = language;
        this.price = price;
        this.password = "123";
    }

    public TeacherProfile () {
//        switch (this.id) {
//            case "1": this.profilePic = R.drawable.teacher_1;
//                break;
//            case "2": this.profilePic = R.drawable.teacher_2;
//                break;
//            case "3": this.profilePic = R.drawable.teacher_3;
//                break;
//            default: this.profilePic = R.drawable.unknown;
//                break;
//        }
        this.rating = 0;
        this.price = 0;
        this.language = "English";
        this.password = "123";
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

    public ArrayList<Review> getReviewList() {
        return reviewList;
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

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setPrice(int price) {
        this.price = price;
    }



    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> mapToReturn = new HashMap<>();
        mapToReturn.put("fullname",this.name);
        mapToReturn.put("mail", this.email);
        mapToReturn.put("price",this.price);
        mapToReturn.put("rating", Double.toString(this.rating));
        mapToReturn.put("language", this.language);
        mapToReturn.put("password", this.password);
        mapToReturn.put("description", this.description);
        return mapToReturn;
    }

    @Override
    public void toObject(String documentId,Map<String, Object> mapOfObject) {
        if(mapOfObject.containsKey("teacher_id")) {
            this.setId((String) mapOfObject.get("teacher_id"));
        }
        else {
            this.setId(documentId);
            this.setName((String) mapOfObject.get("teacher_fullname"));
            this.setEmail((String) mapOfObject.get("teacher_mail"));
            this.setRating(Double.parseDouble((String) mapOfObject.get("teacher_rating")));
            this.setLanguage((String) mapOfObject.get("teacher_language"));
            this.setPrice(Math.toIntExact((long) mapOfObject.get("teacher_price")));
            this.setPassword((String) mapOfObject.get("teacher_password"));
            this.setDescription((String) mapOfObject.get("teacher_description"));
        }
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public String toString() {
        return "Name: " + getName() + "\n Email: " + getEmail() + "\n Rating: " + getRating() + "\n Language: " + getLanguage() + "\n Price: " + getPrice();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setProfilePic(int profilePic) {
        this.profilePic = profilePic;
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
