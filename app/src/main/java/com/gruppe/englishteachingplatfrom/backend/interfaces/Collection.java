package com.gruppe.englishteachingplatfrom.backend.interfaces;

import com.gruppe.englishteachingplatfrom.model.DocumentObject;

import java.util.List;

public interface Collection <T extends DocumentObject> {
    List<T> getAll();
}
