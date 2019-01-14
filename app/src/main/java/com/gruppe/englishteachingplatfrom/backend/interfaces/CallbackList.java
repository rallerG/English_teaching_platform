package com.gruppe.englishteachingplatfrom.backend.interfaces;

import com.gruppe.englishteachingplatfrom.model.DocumentObject;

import java.util.List;

public interface CallbackList <T extends DocumentObject> {
    void onCallback(List<T> listOfObjects);
}
