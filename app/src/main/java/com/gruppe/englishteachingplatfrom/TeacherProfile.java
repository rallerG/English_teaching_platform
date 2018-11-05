package com.gruppe.englishteachingplatfrom;

import java.util.ArrayList;

public class TeacherProfile {
    private static ArrayList<TeacherProfile> pendingProfiles = new ArrayList<TeacherProfile>();
    private static ArrayList<TeacherProfile> favoriteProfiles = new ArrayList<TeacherProfile>();
    private static ArrayList<TeacherProfile> MatchProfiles = new ArrayList<TeacherProfile>();
    //Needs a picture attribute
    private String mName;
    private String mRating;
    private String mTitle;
    private String mPrice;

    public TeacherProfile(String mName, String mRating, String mTitle, String mPrice) {
        this.mName = mName;
        this.mRating = mRating;
        this.mTitle = mTitle;
        this.mPrice = mPrice;
    }


    //TODO make a method that returns a list of ListProfiles, generated from some date
    private static int lastProfileId = 0;

    public static ArrayList<TeacherProfile> createPendingTeacherProfile(int numContacts) {
        if (pendingProfiles.isEmpty()) {
            for (int i = 1; i <= numContacts; i++) {
                pendingProfiles.add(new TeacherProfile("Navn " + ++lastProfileId, Float.toString((float) (Math.random() * 5.0)), "Tutor", Integer.toString((int) (Math.random() * 250.0 + 70)) + " DKK/hr"));
            }
            System.out.println("PROFILER BLEV OPRETTET!!!");
        }

        return pendingProfiles;
    }

    public static ArrayList<TeacherProfile> createFavoriteTeacherProfile(int numContacts) {
        if (favoriteProfiles.isEmpty()) {
            for (int i = 1; i <= numContacts; i++) {
                favoriteProfiles.add(new TeacherProfile("Navn " + ++lastProfileId, Float.toString((float) (Math.random() * 5.0)), "Tutor", Integer.toString((int) (Math.random() * 250.0 + 70)) + " DKK/hr"));
            }
            System.out.println("PROFILER BLEV OPRETTET!!!");
        }

        return favoriteProfiles;
    }

    public static ArrayList<TeacherProfile> createMatchTeacherProfile(int numContacts) {
        if (MatchProfiles.isEmpty()) {
            for (int i = 1; i <= numContacts; i++) {
                MatchProfiles.add(new TeacherProfile("Navn " + ++lastProfileId, Float.toString((float) (Math.random() * 5.0)), "Tutor", Integer.toString((int) (Math.random() * 250.0 + 70)) + " DKK/hr"));
            }
            System.out.println("PROFILER BLEV OPRETTET!!!");
        }

        return MatchProfiles;
    }



    public String getmName() {
        return mName;
    }

    public String getmRating() {
        return mRating;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmPrice() {
        return mPrice;
    }
}
