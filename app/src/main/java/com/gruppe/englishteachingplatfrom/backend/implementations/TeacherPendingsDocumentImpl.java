package com.gruppe.englishteachingplatfrom.backend.implementations;

import com.gruppe.englishteachingplatfrom.backend.interfaces.TeacherPendingsDocument;
import com.gruppe.englishteachingplatfrom.model.TeacherProfile;

public class TeacherPendingsDocumentImpl extends DAOImpl implements TeacherPendingsDocument {
    private String parentTeacherDocumentId;
    public TeacherPendingsDocumentImpl(String parentTeacherDocumentId) {
        super("teachers/"+parentTeacherDocumentId+"/feedback");
        this.parentTeacherDocumentId = parentTeacherDocumentId;
    }

    public String getParentTeacherDocumentId() {
        return parentTeacherDocumentId;
    }

    public void setParentTeacherDocumentId(String parentTeacherDocumentId) {
        this.parentTeacherDocumentId = parentTeacherDocumentId;
    }
}
