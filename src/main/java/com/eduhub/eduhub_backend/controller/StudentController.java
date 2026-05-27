package com.eduhub.eduhub_backend.controller;

import com.eduhub.eduhub_backend.component.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {
    private final Student student;

    public StudentController(Student student) {
        this.student = student;
    }

    @GetMapping("student")
    public ResponseEntity<Student> getStudent(){
        Student student = new Student(1,"Jenith","M");
            return new ResponseEntity<>(student,HttpStatus.OK);
    }

    @GetMapping("students")
    public ResponseEntity<List<Student>> getStudents(){
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(1,"Harini","CH"));
        studentList.add(new Student(2,"Yuva","K"));
        studentList.add(new Student(3,"Jenitha","M"));
        return new ResponseEntity<>(studentList,HttpStatus.OK);
    }

    @GetMapping("{id}/{first-Name}/{last-Name}")
    public ResponseEntity<Student> studentPathVariable(@PathVariable("id") int studentId,
                                                       @PathVariable("first-Name") String firstName,
                                                       @PathVariable("last-Name") String lastName
    ) {
        Student student = new Student(studentId, firstName, lastName);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    //http://localhost:8080/query?studentId=2&firstName=Yuva&lastName=K
    @GetMapping("query")
    public ResponseEntity<Student> studentResponseEntity(@RequestParam int studentId,
                                                         @RequestParam String firstName,
                                                         @RequestParam String lastName){
        Student student = new Student(studentId,firstName,lastName);
        return ResponseEntity.ok(student);
    }

    @PostMapping("create")
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return ResponseEntity.ok(student);
    }

    @PutMapping("{id}/update")
    public ResponseEntity updateStudent(@PathVariable("id") int studentId,
                                        @RequestBody Student student){
        return ResponseEntity.accepted().body(student);
//        return ResponseEntity.badRequest().body(("It doesn't have logic"));
    }
}
