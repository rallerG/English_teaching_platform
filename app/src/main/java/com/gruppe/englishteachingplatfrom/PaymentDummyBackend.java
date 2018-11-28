package com.gruppe.englishteachingplatfrom;

import java.util.ArrayList;

public class PaymentDummyBackend {


    private ArrayList<TeacherDummy> teacherDummies = new ArrayList<>();

    private TeacherDummy t1 = new TeacherDummy("Thomas", "14.04.2018", 270);
    private TeacherDummy t2 = new TeacherDummy("Mikkel", "12.02.2018", 270);
    private TeacherDummy t3 = new TeacherDummy("Julie", "05.03.2018", 270);
    private TeacherDummy t4 = new TeacherDummy("Jonas", "20.07.2018", 270);
    private TeacherDummy t5 = new TeacherDummy("Christoffer", "23.12.2018", 270);
    private TeacherDummy t6 = new TeacherDummy("Knud", "04.07.2018", 270);
    private TeacherDummy t7 = new TeacherDummy("Peter", "01.03.2018", 270);

    public PaymentDummyBackend() {
        teacherDummies.clear();
        teacherDummies.add(t1);
        teacherDummies.add(t2);
        teacherDummies.add(t3);
        teacherDummies.add(t4);
        teacherDummies.add(t5);
        teacherDummies.add(t6);
        teacherDummies.add(t7);
    }


    public ArrayList<TeacherDummy> getTeacherDummies() {
        return teacherDummies;
    }



    class TeacherDummy{
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
