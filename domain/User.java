package com.example.demo.domain;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int UserId;
    @NotBlank
    @Column(unique = true)
    private String emailAddress;
    @NotBlank
    @Column(unique = true)
    private String password;
    @NotBlank
    @Column(unique = true)
    private int cellNumber;
    @NotBlank
    private boolean sendText;
    @NotBlank
    private boolean sendEmail;

    public User(){
    }

    public User(@NotBlank String emailAddress, @NotBlank String password, @NotBlank int cellNumber, @NotBlank boolean sendText, @NotBlank boolean sendEmail) {
        this.emailAddress = emailAddress;
        this.password = password;
        this.cellNumber = cellNumber;
        this.sendText = sendText;
        this.sendEmail = sendEmail;
    }

    public int getUserId() {
        return UserId;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(int cellNumber) {
        this.cellNumber = cellNumber;
    }

    public boolean isSendText() {
        return sendText;
    }

    public void setSendText(boolean sendText) {
        this.sendText = sendText;
    }

    public boolean isSendEmail() {
        return sendEmail;
    }

    public void setSendEmail(boolean sendEmail) {
        this.sendEmail = sendEmail;
    }
}
