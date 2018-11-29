package com.gruppe.englishteachingplatfrom;

import com.gruppe.englishteachingplatfrom.dummy.DummyContent;

import java.util.ArrayList;
import java.util.List;

public class FeedbackProfile {
    private String studName;
    private int rating;
    private String content;
  //  private String date;

    private static ArrayList<FeedbackProfile> feedbackProfiles = new ArrayList<FeedbackProfile>();

    public FeedbackProfile (String studName, int rating, String content){
        this.studName = studName;
        this.rating = rating;
        this.content = content;
    }

//    public static ArrayList<FeedbackProfile> createFeedback(int numContacts) {
//        if (feedbackProfiles.isEmpty()) {
//            for (int i = 1; i <= numContacts; i++) {
//                feedbackProfiles.add(new FeedbackProfile("Paul", 4, "Really good teacher"));
//            }
//        }
//        return feedbackProfiles;
//    }

    public String getStudName() {
        return studName;
    }
    public int getRating() {
        return rating;
    }
    public String getContent() {
        return content;
    }
}
