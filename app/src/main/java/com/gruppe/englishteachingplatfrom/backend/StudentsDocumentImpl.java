package com.gruppe.englishteachingplatfrom.backend;

import com.gruppe.englishteachingplatfrom.backend.interfaces.StudentsDocument;

public class StudentsDocumentImpl extends DAOImpl implements StudentsDocument {
    public StudentsDocumentImpl() {
        super("teachers", Provider.class);
    }
}
