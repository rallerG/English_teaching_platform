package com.gruppe.englishteachingplatfrom.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Payment {
    //private int id;
    private int price;
    private String requestDate;
    private String paymentDate;
    private TeacherProfile teacher;
    private StudentProfile student;
    private boolean isPayed;
    private boolean isActive;

    private Payment(/*int id,*/ int price, String requestDate, String paymentDate, TeacherProfile teacher, StudentProfile student, boolean isPayed, boolean isActive) {
        //this.id = id;
        this.price = price;
        this.requestDate = requestDate;
        this.paymentDate = paymentDate;
        this.teacher = teacher;
        this.student = student;
        this.isPayed = isPayed;
        this.isActive = isActive;
    }


    public static Payment newTransaction(StudentProfile student, TeacherProfile teacher, int price) {
        //id = 0; //Fix
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String theRequestDate = (dateFormat.format(date)).toString();

        return new Payment(/*id,*/ price, theRequestDate, "", teacher, student, false, true);
    }


//    private Payment t1 = new Payment("Thomas", "14.04.2018", 270);
//    private Payment t2 = new Payment("Mikkel", "12.02.2018", 270);
//    private Payment t3 = new Payment("Julie", "05.03.2018", 270);
//    private Payment t4 = new Payment("Jonas", "20.07.2018", 270);
//    private Payment t5 = new Payment("Christoffer", "23.12.2018", 270);
//    private Payment t6 = new Payment("Knud", "04.07.2018", 270);
//    private Payment t7 = new Payment("Peter", "01.03.2018", 270);
//
//    //History list
//    private Payment h1 = new Payment("Adrian", "21.03.2017", 270);
//    private Payment h2 = new Payment("Jacob", "02.07.2017", 270);
//    private Payment h3 = new Payment("Christian", "15.05.2017", 270);
//    private Payment h4 = new Payment("Nikolaj", "21.03.2016", 270);
//    private Payment h5 = new Payment("Sara", "03.11.2017", 270);
//    private Payment h6 = new Payment("Line", "24.01.2015", 270);
//    private Payment h7 = new Payment("Susan", "11.04.2016", 270);
}
