package com.gruppe.englishteachingplatfrom.backend.implementations;

import com.gruppe.englishteachingplatfrom.backend.interfaces.TeachersDocument;
import com.gruppe.englishteachingplatfrom.model.StudentProfile;

public class TeachersDocumentImpl extends DAOImpl<StudentProfile> implements TeachersDocument {
    public TeachersDocumentImpl() {
        super("teachers", StudentProfile.class);
    }
}
