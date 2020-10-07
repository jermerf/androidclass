package com.example.accelerometable.model;

import java.io.Serializable;

public class User implements Serializable {
    private long id = -1;
    private String username;
    private int highscore = 0;

    public User(String username) {
        this(-1, username, 0);
    }
    public User(int id, String username, int highscore) {
        this.id = id;
        this.username = username;
        this.highscore = highscore;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getHighscore() {
        return highscore;
    }

    public void setHighscore(int highscore) {
        this.highscore = highscore;
    }
}
