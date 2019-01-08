package com.gruppe.englishteachingplatfrom.backend;

import com.gruppe.englishteachingplatfrom.backend.interfaces.FeedbackDocument;

public class FeedbackDocumentImpl extends DAOImpl implements FeedbackDocument {
    private String parentTeacherDocumentId;
    public FeedbackDocumentImpl(String parentTeacherDocumentId) {
        super("teachers/"+parentTeacherDocumentId+"/feedback", Provider.class);
        this.parentTeacherDocumentId = parentTeacherDocumentId;
    }

    public String getParentTeacherDocumentId() {
        return parentTeacherDocumentId;
    }

    public void setParentDocumentId(String parentTeacherDocumentId) {
        this.parentTeacherDocumentId = parentTeacherDocumentId;
    }
}
