package com.gruppe.englishteachingplatfrom;

import java.util.ArrayList;

public class SingletonData {

    private static SingletonData Instance = null;
    private static ArrayList names = new ArrayList<String>();
    private static ArrayList prof = new ArrayList<String>();
    private static ArrayList nation = new ArrayList<String>();

    private SingletonData(){
    }
    public static SingletonData getInstance(){
        if(Instance == null){
            Instance = new SingletonData();
        }
        return (Instance);
    }

    public static ArrayList<String> getNames(){
        return names;
    }

    public static ArrayList<String> getProf(){
        return prof;
    }

    public static ArrayList<String> getNation(){
        return nation;
    }


}
