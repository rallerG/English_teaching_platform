package com.gruppe.englishteachingplatfrom.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Payment {
    private static int lastId = 0;
    private int id;
    private int price;
    private String requestDate;
    private String paymentDate;
    private TeacherProfile teacher;
    private StudentProfile student;
    private boolean isPayed;
    private boolean isActive;

    private Payment(int id, int price, String requestDate, String paymentDate, TeacherProfile teacher, StudentProfile student, boolean isPayed, boolean isActive) {
        this.id = id;
        this.price = price;
        this.requestDate = requestDate;
        this.paymentDate = paymentDate;
        this.teacher = teacher;
        this.student = student;
        this.isPayed = isPayed;
        this.isActive = isActive;
    }


    public static void newTransaction(StudentProfile student, TeacherProfile teacher, int price) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String theRequestDate = (dateFormat.format(date)).toString();


        Payment obj = new Payment(lastId++, price, theRequestDate, "", teacher, student, false, true);
        teacher.getActivePaymentDummies().add(obj);
        student.getActivePaymentDummies().add(obj);

//        System.out.println(teacher.getActivePaymentDummies().get(0).toString());
//        System.out.println(student.getActivePaymentDummies().get(0).toString());
//        return new Payment(/*id,*/ price, theRequestDate, "", teacher, student, false, true);
    }

    //TODO Create pay method that flips booleans and adds payment date
    public static void payTransaction(Payment payment) {
        payment.setActive(false);
        payment.setPayed(true);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String thePaymentDate = (dateFormat.format(date)).toString();
        payment.setPaymentDate(thePaymentDate);

        payment.getStudent().getHistoryPaymentDummies().add(payment);
        payment.getStudent().getActivePaymentDummies().remove(payment);

        payment.getTeacher().getHistoryPaymentDummies().add(payment);
        payment.getTeacher().getActivePaymentDummies().remove(payment);

    }

    public static void deleteTransaction(Payment payment) {
        payment.getStudent().getActivePaymentDummies().remove(payment);
        payment.getTeacher().getActivePaymentDummies().remove(payment);
        
    }


    public int getId() {
        return id;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public int getPrice() {
        return price;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public TeacherProfile getTeacher() {
        return teacher;
    }

    public StudentProfile getStudent() {
        return student;
    }

    public boolean isPayed() {
        return isPayed;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setPayed(boolean payed) {
        isPayed = payed;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String toString() {
        return "Price: " + getPrice() + "\n RequestDate: " + getRequestDate() + "\n PaymentDate: " + getPaymentDate() + "\n isPayed: " + isPayed + "\n isActive: " + isActive();
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
