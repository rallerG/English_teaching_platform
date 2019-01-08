package com.gruppe.englishteachingplatfrom.backend.interfaces;

import com.gruppe.englishteachingplatfrom.model.DocumentObject;

public interface Document  {
    void add(DocumentObject document);
    void update(String id, DocumentObject newDocument);
    DocumentObject get(String id);
    void delete(String id);
}
