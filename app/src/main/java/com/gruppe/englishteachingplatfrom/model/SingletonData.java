package com.gruppe.englishteachingplatfrom.model;

import com.gruppe.englishteachingplatfrom.R;

import java.util.ArrayList;

public class SingletonData {

    private static SingletonData Instance = null;
    private static ArrayList<TeacherProfile> teachers = new ArrayList<>();

    private SingletonData(){
    }
    public static SingletonData getInstance(){
        if(Instance == null){
            Instance = new SingletonData();

            teachers.add(new TeacherProfile("0", "3", "English Teacher", "1", R.drawable.profile1));
            teachers.add(new TeacherProfile("1", "4", "English Teacher", "2", R.drawable.profile2));
            teachers.add(new TeacherProfile("2", "1", "English Teacher", "3", R.drawable.profile3));
            teachers.add(new TeacherProfile("3", "5", "English Teacher", "4", R.drawable.profile3));
            teachers.add(new TeacherProfile("4", "5", "English Teacher", "5", R.drawable.profile3));
            teachers.add(new TeacherProfile("5", "5", "English Teacher", "6", R.drawable.profile3));
            teachers.add(new TeacherProfile("6", "5", "English Teacher", "7", R.drawable.profile3));
            teachers.add(new TeacherProfile("7", "5", "English Teacher", "8", R.drawable.profile3));
            teachers.add(new TeacherProfile("8", "5", "English Teacher", "9", R.drawable.profile3));

        }
        return (Instance);
    }



    public  ArrayList<TeacherProfile> getTeacher(){
        return teachers;
    }
}
