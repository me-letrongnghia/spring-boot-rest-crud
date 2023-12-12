package com.nghiale.demo.rest;

import com.nghiale.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    public List<Student> theStudents;
    @PostConstruct
    public void loadData() {

        theStudents = new ArrayList<>();

        theStudents.add(new Student("Nghia", "Le"));
        theStudents.add(new Student("To", "Le"));
        theStudents.add(new Student("Trung", "Nguyen"));
        theStudents.add(new Student("Thinh", "Tran"));
    }

    @GetMapping("/students")
    public List<Student> getStudent() {
        return theStudents;
    }

    @GetMapping("/students/{studentID}")
    public Student getStudent(@PathVariable int studentID) {

        // check the studentID against list size
        if (studentID >= theStudents.size() || (studentID < 0)) {
            throw new StudentNotFoundException("Student ID not found - " + studentID);
        }

        return  theStudents.get(studentID);
    }

//    // add exception handler ... to catch exception
//    @ExceptionHandler
//    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc) {
//
//        // create a StudentErrorResponse
//        StudentErrorResponse error = new StudentErrorResponse();
//        error.setStatus(HttpStatus.NOT_FOUND.value());
//        error.setMessage(exc.getMessage());
//        error.setTimeStamp(System.currentTimeMillis());
//
//        // return ResponseEntity
//        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
//    }
//
//    // add another exception handler ... to catch any exception (catch all)
//    @ExceptionHandler
//    public ResponseEntity<StudentErrorResponse> handleException(Exception exc) {
//
//        // create a StudentErrorResponse
//        StudentErrorResponse error = new StudentErrorResponse();
//        error.setStatus(HttpStatus.BAD_REQUEST.value());
//        error.setMessage(exc.getMessage());
//        error.setTimeStamp(System.currentTimeMillis());
//
//        // return ResponseEntity
//        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
//    }
}
