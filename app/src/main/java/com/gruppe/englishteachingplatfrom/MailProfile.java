package com.gruppe.englishteachingplatfrom;

import java.util.ArrayList;

public class MailProfile {
    private String studName;
    private String content;
    private boolean favorite;
    private boolean visited;


    public MailProfile (String studName, String content, boolean favorite, boolean visited){
        this.studName = studName;
        this.content = content;
        this.favorite = favorite;
        this.visited = visited;
    }


    public String getStudName() {
        return studName;
    }
    public String getContent() { return content; }
    public boolean getFavorite() { return favorite; }
    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
    public boolean getVisited() { return visited; }
    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}
