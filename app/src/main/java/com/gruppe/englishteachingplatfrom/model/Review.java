package com.gruppe.englishteachingplatfrom.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Review extends DocumentObject {
    private StudentProfile studentProfile;
    private String studentId;
    private String id;
    private double rating;
    private String content;
  //  private String date;

    private static ArrayList<Review> reviewProfiles = new ArrayList<Review>();

    public Review(StudentProfile studentProfile, double rating, String content){
        this.studentProfile = studentProfile;
        this.rating = rating;
        this.content = content;
    }

    public Review() {}

//    public static ArrayList<Review> createFeedback(int numContacts) {
//        if (reviewProfiles.isEmpty()) {
//            for (int i = 1; i <= numContacts; i++) {
//                reviewProfiles.add(new Review("Paul", 4, "Really good teacher"));
//            }
//        }
//        return reviewProfiles;
//    }


    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> mapToReturn = new HashMap<>();
        mapToReturn.put("content",this.content);
        mapToReturn.put("rating", Double.toString(this.rating));
        mapToReturn.put("student_id",this.studentProfile.getId());
        return mapToReturn;
    }

    @Override
    public void toObject(String documentId, Map<String, Object> mapOfObject) {
        this.setStudentId((String) mapOfObject.get("student_id"));
        this.setId(documentId);
        this.setRating(Double.parseDouble((String)  mapOfObject.get("rating")));
        this.setContent((String) mapOfObject.get("content"));

    }

    public StudentProfile getStudentProfile() {
        return studentProfile;
    }

    public void setStudentProfile(StudentProfile studentProfile) {
        this.studentProfile = studentProfile;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}
