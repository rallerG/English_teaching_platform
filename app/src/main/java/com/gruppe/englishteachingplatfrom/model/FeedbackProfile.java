package com.gruppe.englishteachingplatfrom.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FeedbackProfile extends DocumentObject {
    private StudentProfile studentProfile;
    private String id;
    private double rating;
    private String content;
  //  private String date;

    private static ArrayList<FeedbackProfile> feedbackProfiles = new ArrayList<FeedbackProfile>();

    public FeedbackProfile (StudentProfile studentProfile, double rating, String content){
        this.studentProfile = studentProfile;
        this.rating = rating;
        this.content = content;
    }

    public FeedbackProfile () {}

//    public static ArrayList<FeedbackProfile> createFeedback(int numContacts) {
//        if (feedbackProfiles.isEmpty()) {
//            for (int i = 1; i <= numContacts; i++) {
//                feedbackProfiles.add(new FeedbackProfile("Paul", 4, "Really good teacher"));
//            }
//        }
//        return feedbackProfiles;
//    }


    @Override
    public Map<String, Object> toMap() {
        return null;
    }

    @Override
    public void toObject(String documentId, Map<String, Object> mapOfObject) {
        this.setStudentProfile((StudentProfile) mapOfObject.get("student"));
        this.setId(documentId);
        this.setRating((Double) mapOfObject.get("rating"));
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
}
