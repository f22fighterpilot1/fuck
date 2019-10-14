package com.example.demo.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

@Entity
public class CheckFile {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "filepath is required")
    @Column(updatable = false, unique = true)
    private String filepath;
    @NotBlank(message = "filename is required")
    @Column(updatable = false, unique = true)
    private String filename;
    private String hash;
    private Date dateCreated;

    public CheckFile(){
    }

    public CheckFile(@NotBlank String filepath, @NotBlank String filename) {
        this.filepath = filepath;
        this.filename = filename;
        this.dateCreated = new Date();
    }

    public void setHash(String hash){
        this.hash = hash;
    }

    public String getHash() {
        return hash;
    }


    public String getFilename() {
        return filename;
    }

    public int getId() {
        return id;
    }

    public String getFilepath() {
        return filepath;
    }

    public Date getDateCreated() {
        return dateCreated;
    }
}
