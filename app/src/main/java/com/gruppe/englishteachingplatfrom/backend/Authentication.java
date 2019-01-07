package com.gruppe.englishteachingplatfrom.backend;

public interface Authentication {
    boolean isUserLoggedOn(User currentUser);
    User login(String email, String password);
    void signUserOut(User currentUser);
    void forgotPassword(User currentUser);
}
