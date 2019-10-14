package com.example.demo.services;

import com.example.demo.domain.CheckFile;
import com.example.demo.domain.User;
import com.example.demo.repositories.CheckFileRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Statement;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@Service
public class CheckFileService {

    @Autowired
    private CheckFileRepository checkFileRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    NotificationService notificationService;

    public CheckFile sha1(String filepath, String filename){

        CheckFile checkFile = new CheckFile(filepath, filename);

        ProcessBuilder processBuilder = new ProcessBuilder();
        // -- Linux --

        // Run a shell command
        //"/Users/brittonirechukwu/newfile.txt"
        processBuilder.command("shasum", filepath);

        // Run a shell script
        //processBuilder.command("path/to/hello.sh");

        // -- Windows --

        // Run a command
        //processBuilder.command("cmd.exe", "/c", "dir C:\\Users\\mkyong");

        // Run a bat file
        //processBuilder.command("C:\\Users\\mkyong\\hello.bat");

        try {

            Process process = processBuilder.start();

            StringBuilder output = new StringBuilder();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }

            int exitVal = process.waitFor();
            if (exitVal == 0) {
                System.out.println("Success!");
                System.out.println(output);
                checkFile.setHash(output.substring(0,40));
                //checkFile.setHash(output.substring(0,40));
                //System.exit(0);
            } else {
                //abnormal...
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        checkFileRepository.save(checkFile);

        return checkFile;
    }

    public CheckFile findByFileName(String filename){

        CheckFile checkFile = checkFileRepository.findByfilename(filename);

//        if(checkFile == null){
//            throw new ProjectidException("Project ID" + projectId + " does not exist");
//        }

        return checkFile;

    }

    public Set<CheckFile> findAllCheckFile(){
        Set<CheckFile> set = checkFileRepository.findAll();
        return set;
    }

//    public Set<User> findAllUsers(){
//        Set<User> set = userRepository.findAllUserNative();
//
//        return set;
//    }

//    public void compare(){
//        Set<CheckFile> checkFiles = findAllCheckFile();
//        for(CheckFile k : checkFiles){
//
//
//            ProcessBuilder processBuilder = new ProcessBuilder();
//
//            // -- Linux --
//
//            // Run a shell command
//            //"/Users/brittonirechukwu/newfile.txt"
//            processBuilder.command("shasum", k.getFilepath());
//
//            // Run a shell script
//            //processBuilder.command("path/to/hello.sh");
//
//            // -- Windows --
//
//            // Run a command
//            //processBuilder.command("cmd.exe", "/c", "dir C:\\Users\\mkyong");
//
//            // Run a bat file
//            //processBuilder.command("C:\\Users\\mkyong\\hello.bat");
//
//            try {
//
//                Process process = processBuilder.start();
//
//                StringBuilder output = new StringBuilder();
//
//                BufferedReader reader = new BufferedReader(
//                        new InputStreamReader(process.getInputStream()));
//
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    output.append(line + "\n");
//                }
//
//                int exitVal = process.waitFor();
//                if (exitVal == 0) {
//                    if(output.substring(0,40).equals(k.getHash())){
//                        System.out.println("the hash has not changed");
//                        System.out.println(output.substring(0,40) + "and" + k.getHash());
//                    }
//                    else{
//                        System.out.println("the hash has changed");
//                        System.out.println(output.substring(0,40));
//                        System.out.println(k.getHash());
//                        checkFileRepository.updateCheckFile(output.substring(0,40), k.getId());
//
//                    }
//                    //checkFile.setHash(output.substring(0,40));
//                    //System.exit(0);
//                } else {
//                    //abnormal...
//                }
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }


    public void continuousCheck() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        final Runnable beeper = new Runnable() {
            public void run() { Set<CheckFile> checkFiles = findAllCheckFile();
            String message = "";
                for(CheckFile k : checkFiles){


                    ProcessBuilder processBuilder = new ProcessBuilder();

                    // -- Linux --

                    // Run a shell command
                    //"/Users/brittonirechukwu/newfile.txt"
                    processBuilder.command("shasum", k.getFilepath());

                    // Run a shell script
                    //processBuilder.command("path/to/hello.sh");

                    // -- Windows --

                    // Run a command
                    //processBuilder.command("cmd.exe", "/c", "dir C:\\Users\\mkyong");

                    // Run a bat file
                    //processBuilder.command("C:\\Users\\mkyong\\hello.bat");

                    try {

                        Process process = processBuilder.start();

                        StringBuilder output = new StringBuilder();

                        BufferedReader reader = new BufferedReader(
                                new InputStreamReader(process.getInputStream()));

                        String line;
                        while ((line = reader.readLine()) != null) {
                            output.append(line + "\n");
                        }

                        int exitVal = process.waitFor();
                        if (exitVal == 0) {
                            if(output.substring(0,40).equals(k.getHash())){
                                System.out.println("the hash for" + k.getFilename() + " has not changed");
                                System.out.println(output.substring(0,40) + "and" + k.getHash());
                            }
                            else{
                                System.out.println("the hash for" + k.getFilename() + " has changed");
                                System.out.println(output.substring(0,40));
                                System.out.println(k.getHash());
                                message += k.getFilename() + " has been changed and has a new hash of " + k.getHash() + "\n\n This occurred on " + new Date() + "\n\n";



                                checkFileRepository.updateCheckFile(output.substring(0,40), k.getId());




                            }
                            //checkFile.setHash(output.substring(0,40));
                            //System.exit(0);
                        } else {
                            //abnormal...
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                if(!message.equals("")) {
                    notificationService.sendNotification("b155@umbc.edu", "new changes", message);
                }
                message = "";

            }
        };
        final ScheduledFuture<?> beeperHandle =
                scheduler.scheduleAtFixedRate(beeper, 10, 10, TimeUnit.SECONDS);
        scheduler.schedule(new Runnable() {
            public void run() { beeperHandle.cancel(true); }
        }, 60 * 60, TimeUnit.SECONDS);
    }
}
