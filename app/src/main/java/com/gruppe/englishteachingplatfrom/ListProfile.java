package com.gruppe.englishteachingplatfrom;

import java.util.ArrayList;

public class ListProfile {
    //Needs a picture attribute
    private String mName;
    private String mRating;
    private String mTitle;
    private String mPrice;

    public ListProfile(String mName, String mRating, String mTitle, String mPrice) {
        this.mName = mName;
        this.mRating = mRating;
        this.mTitle = mTitle;
        this.mPrice = mPrice;
    }


    //TODO make a method that returns a list of ListProfiles, generated from some date
    private static int lastProfileId = 0;

    public static ArrayList<ListProfile> createListProfile(int numContacts) {
        ArrayList<ListProfile> profiles = new ArrayList<ListProfile>();
        System.out.println("PROFILER BLEV OPRETTET!!!");
        for (int i = 1; i <= numContacts; i++) {
            profiles.add(new ListProfile("Navn " + ++lastProfileId, Integer.toString((int)Math.random() * 5), "Tutor", Integer.toString((int)Math.random() * 250 + 70) + " DKK/hr"));
        }

        return profiles;
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
