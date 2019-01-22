package com.gruppe.englishteachingplatfrom.backend.interfaces;

public interface DocumentQuery {
    void deleteEqualTo (String equalToId, boolean isTeacher);
    void deleteEqualTo (String equalToId,boolean isTeacher, CallbackSuccess callbackSuccess);
    void deleteEqualTo (String equalToId,boolean isTeacher, CallbackSuccess callbackSuccess, CallbackError callbackError);
}
