package com.gruppe.englishteachingplatfrom.backend.implementations;

import com.gruppe.englishteachingplatfrom.backend.interfaces.StudentMatchesDocument;

public class StudentMatchesDocumentImpl extends DAOImpl implements StudentMatchesDocument {
    private String parentStudentDocumentId;
    public StudentMatchesDocumentImpl(String parentStudentDocumentId) {
        super("students/"+parentStudentDocumentId+"/matches", );
        this.parentStudentDocumentId = parentStudentDocumentId;
    }

    public String getParentStudentDocumentId() {
        return parentStudentDocumentId;
    }

    public void setParentStudentDocumentId(String parentStudentDocumentId) {
        this.parentStudentDocumentId = parentStudentDocumentId;
    }
}
