package com.gruppe.englishteachingplatfrom.backend.implementations;

import com.gruppe.englishteachingplatfrom.backend.interfaces.StudentFavoritesDocument;
import com.gruppe.englishteachingplatfrom.model.StudentProfile;
import com.gruppe.englishteachingplatfrom.model.TeacherProfile;

public class StudentFavoritesDocumentImpl extends DAOImpl<TeacherProfile> implements StudentFavoritesDocument {
    private String parentStudentDocumentId;
    public StudentFavoritesDocumentImpl(String parentStudentDocumentId) {
        super("students/"+parentStudentDocumentId+"/favorites", TeacherProfile.class);
        this.parentStudentDocumentId = parentStudentDocumentId;
    }

    public String getParentStudentDocumentId() {
        return parentStudentDocumentId;
    }

    public void setParentStudentDocumentId(String parentStudentDocumentId) {
        this.parentStudentDocumentId = parentStudentDocumentId;
    }
}
