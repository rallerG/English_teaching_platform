package com.gruppe.englishteachingplatfrom.model;

import java.util.ArrayList;

public class PaymentDummyBackend {


    private static PaymentDummyBackend Instance = null;

    private static ArrayList<Payment> teacherDummies = new ArrayList<>();
    private static ArrayList<Payment> teacherDummiesHistory = new ArrayList<>();

    public static PaymentDummyBackend getInstance() {
        if(Instance == null){
            Instance = new PaymentDummyBackend();
        }
        return Instance;
    }

    //Active list
    private TeacherDummy t1 = Payment.newTransaction();
    private TeacherDummy t2 = new TeacherDummy("Mikkel", "12.02.2018", 270);
    private TeacherDummy t3 = new TeacherDummy("Julie", "05.03.2018", 270);
    private TeacherDummy t4 = new TeacherDummy("Jonas", "20.07.2018", 270);
    private TeacherDummy t5 = new TeacherDummy("Christoffer", "23.12.2018", 270);
    private TeacherDummy t6 = new TeacherDummy("Knud", "04.07.2018", 270);
    private TeacherDummy t7 = new TeacherDummy("Peter", "01.03.2018", 270);

    //History list
    private TeacherDummy h1 = new TeacherDummy("Adrian", "21.03.2017", 270);
    private TeacherDummy h2 = new TeacherDummy("Jacob", "02.07.2017", 270);
    private TeacherDummy h3 = new TeacherDummy("Christian", "15.05.2017", 270);
    private TeacherDummy h4 = new TeacherDummy("Nikolaj", "21.03.2016", 270);
    private TeacherDummy h5 = new TeacherDummy("Sara", "03.11.2017", 270);
    private TeacherDummy h6 = new TeacherDummy("Line", "24.01.2015", 270);
    private TeacherDummy h7 = new TeacherDummy("Susan", "11.04.2016", 270);

   public void createList(){
        teacherDummies.clear();
        teacherDummies.add(t1);
        teacherDummies.add(t2);
        teacherDummies.add(t3);
        teacherDummies.add(t4);
        teacherDummies.add(t5);
        teacherDummies.add(t6);
        teacherDummies.add(t7);

        teacherDummiesHistory.add(h1);
        teacherDummiesHistory.add(h2);
        teacherDummiesHistory.add(h3);
        teacherDummiesHistory.add(h4);
        teacherDummiesHistory.add(h5);
        teacherDummiesHistory.add(h6);
        teacherDummiesHistory.add(h7);
    }


    private PaymentDummyBackend() {
    }


    public ArrayList<Payment> getTeacherDummies() {
        return teacherDummies;
    }

    public ArrayList<Payment> getTeacherDummiesHistory() {
        return teacherDummiesHistory;
    }

    public void addToHistory(TeacherDummy teacherAcpt){
        teacherDummiesHistory.add(teacherAcpt);
    }

    public class TeacherDummy{
       private String name;
       private String date;
       private int price;

        TeacherDummy(String name, String date, int price) {
            this.name = name;
            this.date = date;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }
}
