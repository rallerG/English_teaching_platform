package com.gruppe.englishteachingplatfrom.backend.implementations;

import com.gruppe.englishteachingplatfrom.backend.interfaces.StudentMatchesDocument;
import com.gruppe.englishteachingplatfrom.model.TeacherProfile;

public class StudentMatchesDocumentImpl extends DAOImpl<TeacherProfile> implements StudentMatchesDocument {
    private String parentStudentDocumentId;
    public StudentMatchesDocumentImpl(String parentStudentDocumentId) {
        super("students/"+parentStudentDocumentId+"/matches");
        this.parentStudentDocumentId = parentStudentDocumentId;
    }

    public String getParentStudentDocumentId() {
        return parentStudentDocumentId;
    }

    public void setParentStudentDocumentId(String parentStudentDocumentId) {
        this.parentStudentDocumentId = parentStudentDocumentId;
    }
}
