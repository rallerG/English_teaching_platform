package com.gruppe.englishteachingplatfrom.backend.implementations;

import com.gruppe.englishteachingplatfrom.backend.interfaces.StudentsDocument;
import com.gruppe.englishteachingplatfrom.model.StudentProfile;

public class StudentsDocumentImpl extends DAOImpl<StudentProfile> implements StudentsDocument {
    public StudentsDocumentImpl() {
        super("students",StudentProfile.class);
    }
}
