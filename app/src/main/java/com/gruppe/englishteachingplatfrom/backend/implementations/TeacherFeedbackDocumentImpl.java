package com.gruppe.englishteachingplatfrom.backend.implementations;

import com.gruppe.englishteachingplatfrom.backend.interfaces.TeacherFeedbackDocument;
import com.gruppe.englishteachingplatfrom.model.TeacherProfile;

public class TeacherFeedbackDocumentImpl extends DAOImpl implements TeacherFeedbackDocument {
    private String parentTeacherDocumentId;
    public TeacherFeedbackDocumentImpl(String parentTeacherDocumentId) {
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
