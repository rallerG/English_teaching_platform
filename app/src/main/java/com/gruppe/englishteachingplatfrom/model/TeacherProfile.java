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

    public void setProfilePictures() {
        switch (this.id) {
            case "1": this.profilePic = R.drawable.teacher_1;
                break;
            case "1BPfvnxtguzYoGle3LwJ": this.profilePic = R.drawable.teacher_2;
                break;
            case "2N4Hy3hLnugCWr61vT2Y": this.profilePic = R.drawable.teacher_3;
                break;
            case "B7Vp1799P0DIc7GZf98T": this.profilePic = R.drawable.teacher_4;
                break;
            case "EMRY1fYSmNY7dh5TK7XJ": this.profilePic = R.drawable.teacher_5;
                break;
            case "FB9PUlmuE39cflrtBYP3": this.profilePic = R.drawable.teacher_6;
                break;
            case "G6dZNQnivN9ohZSxrOaY": this.profilePic = R.drawable.teacher_7;
                break;
            case "GxJUH6qMi3AebW9ZKCoq": this.profilePic = R.drawable.teacher_8;
                break;
            case "Q2TOLFzu2HWrCwUjQSG4": this.profilePic = R.drawable.teacher_9;
                break;
            case "Q8i3k7o3e8G0UcWvbV3Y": this.profilePic = R.drawable.teacher_10;
                break;
            case "SF8k3CHjfgOziZniMiLV": this.profilePic = R.drawable.teacher_11;
                break;
            case "WG4TyBj0REgegpTDTnpr": this.profilePic = R.drawable.teacher_12;
                break;
            case "a0UTA2Z664O84v3FFMlS": this.profilePic = R.drawable.teacher_13;
                break;
            case "aFE5TS6hPLhGFFsGP2G0": this.profilePic = R.drawable.teacher_14;
                break;
            case "dPMtYv4c8jYXNkE4G6e9": this.profilePic = R.drawable.teacher_15;
                break;
            case "iRjjd0UbFFfmKFzfy5M5": this.profilePic = R.drawable.teacher_16;
                break;
            case "idq324F2gBtk0Czcg0vh": this.profilePic = R.drawable.teacher_17;
                break;
            case "leV07PeMwHmHvn8nNI6m": this.profilePic = R.drawable.teacher_18;
                break;
            case "nTYh2YdI8Il09JOPapKv": this.profilePic = R.drawable.teacher_19;
                break;
            case "pczPCGEmgCIZ4ct6gZAu": this.profilePic = R.drawable.teacher_20;
                break;
            case "qyAzA5Y3h4TgYkxEa0gx": this.profilePic = R.drawable.teacher_21;
                break;
            case "yvHvGQArtiC1jeAMSGAa": this.profilePic = R.drawable.teacher_22;
                break;

                default: this.profilePic = R.drawable.unknown;
                break;
        }
    }
    @Override
    public boolean equals(Object teach) {
        TeacherProfile teacherProfile = (TeacherProfile) teach;
        return id.equals(teacherProfile.getId());
    }

    public String getFirstName() {
        String name = this.getName();
        String[] parts = name.split(" ");
        return parts[0];
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
