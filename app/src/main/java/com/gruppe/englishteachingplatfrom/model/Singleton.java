package com.gruppe.englishteachingplatfrom.model;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.gruppe.englishteachingplatfrom.backend.implementations.StudentsDocumentImpl;
import com.gruppe.englishteachingplatfrom.backend.implementations.TeachersDocumentImpl;
import com.gruppe.englishteachingplatfrom.backend.interfaces.Callback;
import com.gruppe.englishteachingplatfrom.backend.interfaces.CallbackList;
import com.gruppe.englishteachingplatfrom.backend.interfaces.StudentsDocument;
import com.gruppe.englishteachingplatfrom.backend.interfaces.TeachersDocument;
import com.gruppe.englishteachingplatfrom.view.LoginOrSignupActivity;

import java.util.ArrayList;
import java.util.List;

public class Singleton {
    private static Singleton Instance = null;
    private TeacherProfile currrentTeacher = null;
    private StudentProfile currrentStudent = null;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private static ArrayList<TeacherProfile> teacherDummies = new ArrayList<>();
    private static ArrayList<StudentProfile> studentDummies = new ArrayList<>();

    public static Singleton getInstance() {
        if(Instance == null){
            Instance = new Singleton();
        }
        return Instance;
    }

//    //Active list
//    private TeacherProfile t1 = new TeacherProfile("Hans", 2, 0, "English",  299);
//    private TeacherProfile t2 = new TeacherProfile("Mikkel", 4, 0, "English",  299);
//    private TeacherProfile t3 = new TeacherProfile("Julie", 1, 0, "English", 299);
//    private TeacherProfile t4 = new TeacherProfile("Jonas", 2, 0, "English", 299);
//    private TeacherProfile t5 = new TeacherProfile("Christoffer", 0, 0, "English", 299);
//    private TeacherProfile t6 = new TeacherProfile("Knud", 5, 0, "English", 299);
//    private TeacherProfile t7 = new TeacherProfile("Peter", 5, 0, "English", 299);
//
//    //History list
//    private StudentProfile h1 = new StudentProfile("Adrian", "adrian@hotmail.com", 0);
//    private StudentProfile h2 = new StudentProfile("Jacob", "jacob@gmail.com", 0);
//    private StudentProfile h3 = new StudentProfile("Christian", "christian@tdcmail.dk", 0);
//    private StudentProfile h4 = new StudentProfile("Nikolaj", "nikolaj@yahoo.com", 0);
//    private StudentProfile h5 = new StudentProfile("Sara", "sara@live.dk", 0);
//    private StudentProfile h6 = new StudentProfile("Line", "line@sol.dk", 0);
//    private StudentProfile h7 = new StudentProfile("Susan", "susan@jubii.dk", 0);
//    private StudentProfile h8 = new StudentProfile("Susan", "susan@jubii.dk", 0);
//    private StudentProfile h9 = new StudentProfile("Susan", "susan@jubii.dk", 0);
//    private StudentProfile h10 = new StudentProfile("Susan", "susan@jubii.dk", 0);
//    private StudentProfile h11 = new StudentProfile("Susan", "susan@jubii.dk", 0);
//    private StudentProfile h12 = new StudentProfile("Susan", "susan@jubii.dk", 0);


//    public void createList(){
//        teacherDummies.clear();
//        teacherDummies.add(t1);
//        teacherDummies.add(t2);
//        teacherDummies.add(t3);
//        teacherDummies.add(t4);
//        teacherDummies.add(t5);
//        teacherDummies.add(t6);
//        teacherDummies.add(t7);
//
//        studentDummies.clear();
//        studentDummies.add(h1);
//        studentDummies.add(h2);
//        studentDummies.add(h3);
//        studentDummies.add(h4);
//        studentDummies.add(h5);
//        studentDummies.add(h6);
//        studentDummies.add(h7);
//        studentDummies.add(h8);
//        studentDummies.add(h9);
//        studentDummies.add(h10);
//        studentDummies.add(h11);
//        studentDummies.add(h12);
//
//        teacherDummies.get(0).getMatchProfiles().clear();
//        teacherDummies.get(0).getMatchProfiles().addAll(studentDummies);
//    }


    private Singleton() {
    }


    public ArrayList<TeacherProfile> getTeacherDummies() {
        return teacherDummies;
    }

    public ArrayList<StudentProfile> getStudentDummies() {
        return studentDummies;
    }

    public TeacherProfile getCurrrentTeacher() {
        return currrentTeacher;
    }

    public void setCurrrentTeacher(TeacherProfile currrentTeacher) {
        this.currrentTeacher = currrentTeacher;
    }

    public void logout() {
        editor.clear().commit();
        currrentStudent = null;
        currrentTeacher = null;
        studentDummies.clear();
        teacherDummies.clear();
    }

    public StudentProfile getCurrrentStudent() {
        return currrentStudent;
    }

    public void setCurrrentStudent(StudentProfile currrentStudent) {
        this.currrentStudent = currrentStudent;
    }

    public void rememberTeacher() {
        editor.putString("current_teacher_id", currrentTeacher.getId()).commit();
    }

    public void rememberStudent() {
        editor.putString("current_student_id", currrentStudent.getId()).commit();
    }

    public SharedPreferences getPreferences() {
        return preferences;
    }

    public void setPreferences(SharedPreferences preferences) {
        this.editor = preferences.edit();
        this.preferences = preferences;
    }


//    public static void downloadAllStudentProfiles() {
//        StudentsDocument studentsDocument = new StudentsDocumentImpl();
//        studentsDocument.getAll(new CallbackList<StudentProfile>() {
//            @Override
//            public void onCallback(List<StudentProfile> listOfObjects) {
//                for (StudentProfile student : listOfObjects) {
//                    System.out.println(student.toString());
//                }
//                studentDummies.clear();
//                studentDummies.addAll(listOfObjects);
//            }
//        });
//    }

//    public static void downloadAllTeachersProfiles() {
//        TeachersDocument teachersDocument = new TeachersDocumentImpl();
//        teachersDocument.getAll(new CallbackList<TeacherProfile>() {
//            @Override
//            public void onCallback(List<TeacherProfile> listOfObjects) {
//                for (TeacherProfile teacher : listOfObjects) {
//                    System.out.println(teacher.toString());
//                }
//                teacherDummies.clear();
//                teacherDummies.addAll(listOfObjects);
//                teacherDummies.addAll(listOfObjects);
//                teacherDummies.addAll(listOfObjects);
//                teacherDummies.addAll(listOfObjects);
//            }
//        });
//    }

//    public void addToHistory(TeacherDummy teacherAcpt){
//        studentDummies.add(teacherAcpt);
//    }

//    private static int lastTeacherProfileId = 0;
//    public static ArrayList<TeacherProfile> createRandomTeacherProfile(int numContacts) {
//            for (int i = 1; i <= numContacts; i++) {
//                teacherDummies.add(new TeacherProfile("Navn ", (Math.random() * 5.0) , 0, "English", (int) (Math.random() * 250.0 + 70)));
//            }
//            System.out.println("PROFILER BLEV OPRETTET!!!");
//
//        return teacherDummies;
//    }
//
//    private static int lastStudentProfileId = 0;
//    public static ArrayList<StudentProfile> createRandomStudentProfile(int numContacts) {
//            for (int i = 1; i <= numContacts; i++) {
//                studentDummies.add(new StudentProfile("" + ++lastStudentProfileId, "Navn ", "test@email.com", 0, (Math.random() * 250.0 + 70)));
//            }
//            System.out.println("PROFILER BLEV OPRETTET!!!");
//
//        return studentDummies;
//    }
}



//    private static ArrayList<String> names = new ArrayList<String>();
//    private static ArrayList<String> prof = new ArrayList<String>();
//    private static ArrayList<String> nation = new ArrayList<String>();
