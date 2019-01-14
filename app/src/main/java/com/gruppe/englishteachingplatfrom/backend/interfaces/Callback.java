package com.gruppe.englishteachingplatfrom.backend.interfaces;

import com.gruppe.englishteachingplatfrom.model.DocumentObject;

public interface Callback <T extends DocumentObject> {
    void onCallback(T object);
}
