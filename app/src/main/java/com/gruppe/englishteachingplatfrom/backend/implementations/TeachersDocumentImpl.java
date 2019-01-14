package com.gruppe.englishteachingplatfrom.backend.implementations;

import com.gruppe.englishteachingplatfrom.backend.interfaces.TeachersDocument;
import com.gruppe.englishteachingplatfrom.model.StudentProfile;
import com.gruppe.englishteachingplatfrom.model.TeacherProfile;

public class TeachersDocumentImpl extends DAOImpl<TeacherProfile> implements TeachersDocument {
    public TeachersDocumentImpl() {
        super("teachers");
    }
}
