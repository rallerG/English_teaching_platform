package com.gruppe.englishteachingplatfrom.backend;

public interface DocumentObject<T>  {
    void add(T object);
    void update(int id, T newObject);
    T get(int id);
    void delete(int id);
}
