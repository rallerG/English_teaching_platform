package com.gruppe.englishteachingplatfrom.backend;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

//Needs to be modified to a singleton
public class AuthenticationImpl implements Authentication {
    private FirebaseAuth auth;

    public AuthenticationImpl () {
        auth = FirebaseAuth.getInstance();
    }

    @Override
    public boolean isUserLoggedOn(User currentUser) {
        FirebaseUser firebaseUser = auth.getCurrentUser();
        if (firebaseUser == null)
            return false;
        return true;
    }

    @Override
    public User login(String email, String password, Activity goToActivityIfSuccess) {
        auth.signInWithCustomToken("token")
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //If sign in is succeeded, returns true
                            FirebaseUser user = auth.getCurrentUser();
                        } else {
                            // If sign in fails return null
                           FirebaseUser user =
                        }
                    }

                });
    }

    @Override
    public void signUserOut(User currentUser) {
        FirebaseAuth.getInstance().signOut();
    }

    @Override
    public void forgotPassword(User currentUser) {

    }
}
