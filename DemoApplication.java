package com.example.demo;

import com.example.demo.services.CheckFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@SpringBootApplication
public class DemoApplication {

    @Autowired
    private CheckFileService checkFileService;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);


    }
    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        checkFileService.continuousCheck();
    }

}
