package com.gruppe.englishteachingplatfrom;

import java.util.ArrayList;

public class MailProfile {
    private String studName;
    private String content;
    private boolean favorite;


    public MailProfile (String studName, String content, boolean favorite){
        this.studName = studName;
        this.content = content;
        this.favorite = favorite;
    }


    public String getStudName() {
        return studName;
    }
    public String getContent() { return content; }
    public boolean getFavorite() { return favorite; }
}
