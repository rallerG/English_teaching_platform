package com.gruppe.englishteachingplatfrom.backend.implementations;

import com.gruppe.englishteachingplatfrom.backend.interfaces.StudentPendingsDocument;
import com.gruppe.englishteachingplatfrom.model.TeacherProfile;

public class StudentPendingsDocumentImpl extends DAOImpl<TeacherProfile> implements StudentPendingsDocument {
    private String parentStudentDocumentId;
    public StudentPendingsDocumentImpl(String parentStudentDocumentId) {
        super("students/"+parentStudentDocumentId+"/pendings");
        this.parentStudentDocumentId = parentStudentDocumentId;
    }

    public String getParentStudentDocumentId() {
        return parentStudentDocumentId;
    }

    public void setParentStudentDocumentId(String parentStudentDocumentId) {
        this.parentStudentDocumentId = parentStudentDocumentId;
    }
}
