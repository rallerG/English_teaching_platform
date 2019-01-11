package com.gruppe.englishteachingplatfrom.model;

import com.gruppe.englishteachingplatfrom.backend.implementations.DAOImpl;
import com.gruppe.englishteachingplatfrom.backend.implementations.StudentsDocumentImpl;
import com.gruppe.englishteachingplatfrom.backend.interfaces.StudentsDocument;

public class Test {

    public static void main(String [] args) {
        StudentsDocument studentdocument = new StudentsDocumentImpl();
        System.out.println(studentdocument.getAll());
    }

}
