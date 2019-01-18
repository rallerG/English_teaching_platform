package com.gruppe.englishteachingplatfrom.model;

import com.gruppe.englishteachingplatfrom.backend.implementations.PaymentDocumentImpl;
import com.gruppe.englishteachingplatfrom.backend.interfaces.CallbackSuccess;
import com.gruppe.englishteachingplatfrom.backend.interfaces.PaymentDocument;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Payment extends DocumentObject {
    private String id;
    private int price;
    private String requestDate;
    private String paymentDate;
    private String teacherId;
    private String studentId;
    private TeacherProfile teacher;
    private StudentProfile student;
    private boolean isPayed;
    private boolean isActive;

    private Payment(int price, String requestDate, String paymentDate, TeacherProfile teacher, StudentProfile student, boolean isPayed, boolean isActive) {

        this.price = price;
        this.requestDate = requestDate;
        this.paymentDate = paymentDate;
        this.teacher = teacher;
        this.student = student;
        this.isPayed = isPayed;
        this.isActive = isActive;
    }

    public Payment (){}

    public static void newTransaction(final StudentProfile student, final TeacherProfile teacher, int price) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String theRequestDate = (dateFormat.format(date)).toString();

        final Payment obj = new Payment( price, theRequestDate, "", teacher, student, false, true);
        
        PaymentDocument paymentDocument = new PaymentDocumentImpl();
        paymentDocument.add(obj, new CallbackSuccess() {
            @Override
            public void onCallback() {
                teacher.getActivePaymentDummies().add(obj);
                student.getActivePaymentDummies().add(obj);
            }
        });
    }

    //TODO Create pay method that flips booleans and adds payment date
    public static void payTransaction(final Payment payment) {
        payment.setActive(false);
        payment.setPayed(true);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String thePaymentDate = (dateFormat.format(date)).toString();
        payment.setPaymentDate(thePaymentDate);

        PaymentDocument paymentDocument = new PaymentDocumentImpl();
        paymentDocument.update(payment.getId(), payment, new CallbackSuccess() {
            @Override
            public void onCallback() {
                payment.getStudent().getHistoryPaymentDummies().add(payment);
                payment.getStudent().getActivePaymentDummies().remove(payment);

                payment.getTeacher().getHistoryPaymentDummies().add(payment);
                payment.getTeacher().getActivePaymentDummies().remove(payment);
            }
        });
    }

    public static void deleteTransaction(Payment payment) {
        payment.getStudent().getActivePaymentDummies().remove(payment);
        payment.getTeacher().getActivePaymentDummies().remove(payment);
        
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public void setStudent(StudentProfile student) {
        this.student = student;
    }

    public void setTeacher(TeacherProfile teacher) {
        this.teacher = teacher;
    }

    public String getId() {
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

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> mapToReturn = new HashMap<>();
        mapToReturn.put("price",this.getPrice());
        mapToReturn.put("requestDate",this.getRequestDate());
        mapToReturn.put("paymentDate", this.getPaymentDate());
        mapToReturn.put("teacher_id",this.getTeacher().getId());
        mapToReturn.put("student_id",this.getStudent().getId());
        mapToReturn.put("isPayed",this.isPayed());
        mapToReturn.put("isActive",this.isActive());
        return mapToReturn;
    }

    @Override
    public void toObject(String documentId, Map<String, Object> mapOfObject) {
        this.setId(documentId);
        this.setPrice(Math.toIntExact((long) mapOfObject.get("price")));
        this.setRequestDate((String) mapOfObject.get("requestDate"));
        this.setPaymentDate((String) mapOfObject.get("paymentDate"));
        this.setStudentId((String) mapOfObject.get("student_id"));
        this.setTeacherId((String) mapOfObject.get("teacher_id"));
        this.setPayed((boolean) mapOfObject.get("isPayed"));
        this.setActive((boolean) mapOfObject.get("isActive"));
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
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
