package com.example.demo.exceptions;

public class CheckFileidExceptionResponse {

    private String filename;

    public CheckFileidExceptionResponse(String filename){
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
