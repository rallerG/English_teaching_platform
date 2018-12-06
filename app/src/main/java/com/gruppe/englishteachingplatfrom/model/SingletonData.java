package com.gruppe.englishteachingplatfrom.model;

import java.util.ArrayList;

public class SingletonData {

    private static SingletonData Instance = null;
    private static ArrayList<String> names = new ArrayList<String>();
    private static ArrayList<String> prof = new ArrayList<String>();
    private static ArrayList<String> nation = new ArrayList<String>();

    private SingletonData(){
    }
    public static SingletonData getInstance(){
        if(Instance == null){
            Instance = new SingletonData();
        }
        return (Instance);
    }

    public  ArrayList<String> getNames(){
        return names;
    }

    public  ArrayList<String> getProf(){
        return prof;
    }

    public  ArrayList<String> getNation(){
        return nation;
    }


}
