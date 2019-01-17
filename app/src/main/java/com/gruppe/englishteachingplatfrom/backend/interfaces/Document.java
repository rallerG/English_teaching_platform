package com.gruppe.englishteachingplatfrom.backend.interfaces;

import com.gruppe.englishteachingplatfrom.model.DocumentObject;

public interface Document  {
    void add(DocumentObject document);
    void add(String referenceId, boolean isTeacher);
    void update(String id, DocumentObject newDocument);
    void get(String id, Callback callback);
    void delete(String id);
}
