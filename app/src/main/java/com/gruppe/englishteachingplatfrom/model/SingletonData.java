package com.gruppe.englishteachingplatfrom.model;

import java.util.ArrayList;

public class SingletonData {

    private static SingletonData Instance = null;
    private static ArrayList<TeacherProfile> teachers = new ArrayList<>();

    private SingletonData(){
    }
    public static SingletonData getInstance(){
        if(Instance == null){
            Instance = new SingletonData();
        }
        return (Instance);
    }

    public  ArrayList<TeacherProfile> getTeacher(){
        return teachers;
    }
}
