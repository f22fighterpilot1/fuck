package com.example.demo.domain;

import javax.persistence.*;

@Entity
public class Watchlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private int UserId;
    private String filepath;
    private String filename;
    private String hash;

    public Watchlist(){
    }

    public Watchlist(int userId, String filepath, String filename, String hash) {
        UserId = userId;
        this.filepath = filepath;
        this.filename = filename;
        this.hash = hash;
    }

    public int getId() {
        return Id;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
