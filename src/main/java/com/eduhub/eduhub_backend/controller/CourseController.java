package com.eduhub.eduhub_backend.controller;

import com.eduhub.eduhub_backend.component.Course;
import com.eduhub.eduhub_backend.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("courses")
public class CourseController {

    static List<Course> courseList = new ArrayList<>();

    static {
        courseList.add(new Course("CS100","Fullstack",4));
        courseList.add(new Course("CS101","OS",4));
        courseList.add(new Course("CS102","Cloud computing",3));
        courseList.add(new Course("CS103","Java",3));
        courseList.add(new Course("CS104","Python",2));
    }

    @GetMapping("courses")
    public ResponseEntity<List<Course>> getCourses(){
        return new ResponseEntity<>(courseList,HttpStatus.OK);
    }

    // http://localhost:8080/courses/CS101
    @GetMapping("{coursecode}")
    public ResponseEntity<Course> getCourse(
            @PathVariable("coursecode") String coursecode){

        return courseList.stream()
                .filter(c -> c.getCoursecode().equalsIgnoreCase(coursecode))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Course",
                                "CourseCode",
                                coursecode));
    }

    @GetMapping("{coursecode}/{subjectname}/{credits}")
    public ResponseEntity<Course> coursePathVariable(
            @PathVariable("coursecode") String coursecode,
            @PathVariable("subjectname") String subjectname,
            @PathVariable("credits") int credits){

        Course course = new Course(coursecode, subjectname, credits);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @GetMapping("coursequery")
    public ResponseEntity<Course> courseResponseEntity(
            @RequestParam String coursecode,
            @RequestParam String subjectname,
            @RequestParam int credits){

        Course course = new Course(coursecode,subjectname,credits);
        return ResponseEntity.ok(course);
    }

    @PostMapping("createcourse")
    public ResponseEntity<Course> createCourse(
            @RequestBody Course course){

        courseList.add(course);
        return ResponseEntity.ok(course);
    }

    @PutMapping("{coursecode}/update")
    public ResponseEntity updateCourse(
            @PathVariable("coursecode") String coursecode,
            @RequestBody Course updateCourse){

        Course course = courseList.stream()
                .filter(c -> c.getCoursecode().equalsIgnoreCase(coursecode))
                .findFirst()
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Course",
                                "CourseCode",
                                coursecode));

        course.setCoursecode(updateCourse.getCoursecode());
        course.setSubjectname(updateCourse.getSubjectname());
        course.setCredits(updateCourse.getCredits());

        return ResponseEntity.accepted().body(course);
    }

    // Delete Course
    @DeleteMapping("{coursecode}/delete")
    public ResponseEntity deleteCourse(
            @PathVariable("coursecode") String coursecode){

        Course course = courseList.stream()
                .filter(c -> c.getCoursecode().equalsIgnoreCase(coursecode))
                .findFirst()
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Course",
                                "CourseCode",
                                coursecode));

        courseList.remove(course);
        return ResponseEntity.ok("Deleted Successfully");
    }

    @PutMapping("query/{coursecode}")
    public String queryCourse(@PathVariable String coursecode){

        if(coursecode.startsWith("*")){
            throw new IllegalArgumentException(
                    "It is having special character");
        }else if(coursecode.startsWith("6")){
            throw new RuntimeException();
        }
        return coursecode;
    }
}