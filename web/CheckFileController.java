package com.example.demo.web;

import com.example.demo.domain.CheckFile;
import com.example.demo.services.CheckFileService;
import com.example.demo.services.MapValidationErrorService;
import com.example.demo.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/api/check")
@CrossOrigin
public class CheckFileController {

    @Autowired
    private CheckFileService checkFileService;
    @Autowired
    private NotificationService notificationService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody CheckFile checkFile, BindingResult result){

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null){
            return errorMap;
        }

        CheckFile checkFile1 = checkFileService.sha1(checkFile.getFilepath(), checkFile.getFilename());


        return new ResponseEntity<CheckFile>(checkFile1, HttpStatus.CREATED);
    }

    @GetMapping("/{filename}")
    public ResponseEntity<?> getProjectById(@PathVariable String filename) throws InterruptedException {

        CheckFile checkFile = checkFileService.findByFileName(filename);



        return new ResponseEntity<CheckFile>(checkFile, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() throws InterruptedException {

        Set<CheckFile> set;
        set = checkFileService.findAllCheckFile();

        //checkFileService.continuousCheck();

        System.out.println(set);
        return new ResponseEntity<Set<CheckFile>>(set, HttpStatus.OK);
    }

    @GetMapping("/britton")
    public ResponseEntity<?> kill(){

        checkFileService.continuousCheck();



        return new ResponseEntity<>(HttpStatus.OK);
    }

}
