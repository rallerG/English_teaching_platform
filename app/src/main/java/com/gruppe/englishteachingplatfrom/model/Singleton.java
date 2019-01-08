package com.gruppe.englishteachingplatfrom.model;

import java.util.ArrayList;

public class Singleton {


    private static Singleton Instance = null;

    private static ArrayList<TeacherProfile> teacherDummies = new ArrayList<>();
    private static ArrayList<StudentProfile> studentDummies = new ArrayList<>();

    public static Singleton getInstance() {
        if(Instance == null){
            Instance = new Singleton();
        }
        return Instance;
    }

    //Active list
    private TeacherProfile t1 = new TeacherProfile("Hans", "0", "Teacher", 270, "English", 299);
    private TeacherProfile t2 = new TeacherProfile("Mikkel", "0", "Teacher", 270, "English", 299);
    private TeacherProfile t3 = new TeacherProfile("Julie", "0", "Teacher", 270, "English", 299);
    private TeacherProfile t4 = new TeacherProfile("Jonas", "0", "Teacher", 270, "English", 299);
    private TeacherProfile t5 = new TeacherProfile("Christoffer", "0", "Teacher", 270, "English", 299);
    private TeacherProfile t6 = new TeacherProfile("Knud", "0", "Teacher", 270, "English", 299);
    private TeacherProfile t7 = new TeacherProfile("Peter", "0", "Teacher", 270, "English", 299);

    //History list
    private StudentProfile h1 = new StudentProfile("Adrian", "adrian@hotmail.com", 0, 0);
    private StudentProfile h2 = new StudentProfile("Jacob", "jacob@gmail.com", 0, 0);
    private StudentProfile h3 = new StudentProfile("Christian", "christian@tdcmail.dk", 0, 0);
    private StudentProfile h4 = new StudentProfile("Nikolaj", "nikolaj@yahoo.com", 0, 0);
    private StudentProfile h5 = new StudentProfile("Sara", "sara@live.dk", 0, 0);
    private StudentProfile h6 = new StudentProfile("Line", "line@sol.dk", 0, 0);
    private StudentProfile h7 = new StudentProfile("Susan", "susan@jubii.dk", 0, 0);


    public void createList(){
        teacherDummies.clear();
        teacherDummies.add(t1);
        teacherDummies.add(t2);
        teacherDummies.add(t3);
        teacherDummies.add(t4);
        teacherDummies.add(t5);
        teacherDummies.add(t6);
        teacherDummies.add(t7);

        studentDummies.clear();
        studentDummies.add(h1);
        studentDummies.add(h2);
        studentDummies.add(h3);
        studentDummies.add(h4);
        studentDummies.add(h5);
        studentDummies.add(h6);
        studentDummies.add(h7);
    }


    private Singleton() {
    }



    public ArrayList<TeacherProfile> getTeacherDummies() {
        return teacherDummies;
    }

    public ArrayList<StudentProfile> getStudentDummies() {
        return studentDummies;
    }

//    public void addToHistory(TeacherDummy teacherAcpt){
//        studentDummies.add(teacherAcpt);
//    }

    private static int lastTeacherProfileId = 0;
    public static ArrayList<TeacherProfile> createRandomTeacherProfile(int numContacts) {
            for (int i = 1; i <= numContacts; i++) {
                teacherDummies.add(new TeacherProfile("Navn " + ++lastTeacherProfileId, Float.toString((float) (Math.random() * 5.0)), Integer.toString((int) (Math.random() * 250.0 + 70)), 0, "English", 299));
            }
            System.out.println("PROFILER BLEV OPRETTET!!!");

        return teacherDummies;
    }

    private static int lastStudentProfileId = 0;
    public static ArrayList<StudentProfile> createRandomStudentProfile(int numContacts) {
            for (int i = 1; i <= numContacts; i++) {
                studentDummies.add(new StudentProfile("" + ++lastStudentProfileId, "Navn ", "test@email.com", 0, (Math.random() * 250.0 + 70)));
            }
            System.out.println("PROFILER BLEV OPRETTET!!!");

        return studentDummies;
    }
}



//    private static ArrayList<String> names = new ArrayList<String>();
//    private static ArrayList<String> prof = new ArrayList<String>();
//    private static ArrayList<String> nation = new ArrayList<String>();
