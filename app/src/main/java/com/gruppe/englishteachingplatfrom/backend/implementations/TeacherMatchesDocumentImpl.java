package com.gruppe.englishteachingplatfrom.backend.implementations;

import com.gruppe.englishteachingplatfrom.backend.interfaces.TeacherMatchesDocument;

public class TeacherMatchesDocumentImpl extends DAOImpl implements TeacherMatchesDocument {
    private String parentTeacherDocumentId;
    public TeacherMatchesDocumentImpl(String parentTeacherDocumentId) {
        super("teachers/"+parentTeacherDocumentId+"/matches", );
        this.parentTeacherDocumentId = parentTeacherDocumentId;
    }

    public String getParentTeacherDocumentId() {
        return parentTeacherDocumentId;
    }

    public void setParentTeacherDocumentId(String parentTeacherDocumentId) {
        this.parentTeacherDocumentId = parentTeacherDocumentId;
    }
}
