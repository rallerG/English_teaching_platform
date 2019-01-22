package com.gruppe.englishteachingplatfrom.backend.interfaces;

import com.gruppe.englishteachingplatfrom.model.DocumentObject;

public interface Document  {
    void add(DocumentObject document);
    void add(DocumentObject document, CallbackSuccess callbackSuccess);
    void add(DocumentObject document, CallbackSuccess callbackSuccess, CallbackError callbackError);
    void add(String referenceId, boolean isTeacher);
    void add(String referenceId, boolean isTeacher, CallbackSuccess callbackSuccess);
    void add(String referenceId, boolean isTeacher, CallbackSuccess callbackSuccess, CallbackError callbackError);
    void update(String id, DocumentObject newDocument);
    void update(String id, DocumentObject newDocument, CallbackSuccess callbackSuccess);
    void update(String id, DocumentObject newDocument, CallbackSuccess callbackSuccess, CallbackError callbackError);
    void get(String id, Callback callback);
    void get(String id, Callback callback, CallbackError callbackError);
    void delete(String id);
    void delete(String id, CallbackSuccess callbackSuccess);
    void delete(String id, CallbackSuccess callbackSuccess, CallbackError callbackError);
}
