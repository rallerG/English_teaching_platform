package com.gruppe.englishteachingplatfrom.backend;

public interface UserProfile extends Authentication {
    User getUserProfile();
    Provider getUserProfileProvider();
    void updateUserProfile();
    void changeEmail(String newEmail);
    void changePassword(String newPassword);
    void deleteUser();
}
