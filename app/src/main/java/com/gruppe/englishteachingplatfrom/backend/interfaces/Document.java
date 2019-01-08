package com.gruppe.englishteachingplatfrom.backend.interfaces;

public interface Document<T>  {
    void add(T document);
    void update(String id, T newDocument);
    T get(String id);
    void delete(String id);
}
