package com.gruppe.englishteachingplatfrom.backend.implementations;

import com.gruppe.englishteachingplatfrom.backend.interfaces.TeacherReviewDocument;
import com.gruppe.englishteachingplatfrom.model.Review;

public class TeacherReviewDocumentImpl extends DAOImpl<Review> implements TeacherReviewDocument {
    private String parentTeacherDocumentId;
    public TeacherReviewDocumentImpl(String parentTeacherDocumentId) {
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
