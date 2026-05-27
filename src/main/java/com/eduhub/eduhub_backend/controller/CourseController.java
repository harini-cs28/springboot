package com.eduhub.eduhub_backend.controller;

import com.eduhub.eduhub_backend.component.Course;
import com.eduhub.eduhub_backend.component.DemoService;
import com.eduhub.eduhub_backend.component.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("courses")
public class CourseController {
    /*@Autowired //dependency - injection
    Course courseService;
    /*public CourseController(CourseService courseService){
        this.courseService = courseService;
    } */
    /*@Autowired
    DemoService demoService;

    @GetMapping("get-course")
    public String getCourse(){
        return courseService.getCourse();
    }

    @GetMapping("get-service")
    public String getService(){
        return demoService.getService();
    } */
    private final Course course;

    public CourseController(Course course) {
        this.course = course;
    }

    @GetMapping("course")
    public ResponseEntity<Course> getCourse(){
        Course course = new Course(100,"Fullstack",4);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @GetMapping("courses")
    public ResponseEntity<List<Course>> getCourses(){
        List<Course> courseList = new ArrayList<>();
        courseList.add(new Course(100,"Fullstack",4));
        courseList.add(new Course(101,"OS",4));
        courseList.add(new Course(102,"Cloud computing",3));
        courseList.add(new Course(103,"Java",3));
        courseList.add(new Course(104,"Python",2));
        return new ResponseEntity<>(courseList,HttpStatus.OK);
    }

    @GetMapping("{coursecode}/{subjectname}/{credits}")
    public ResponseEntity<Course> coursePathVariable(@PathVariable("coursecode") int coursecode,
                                                     @PathVariable("subjectname") String subjectname,
                                                      @PathVariable("credits") int credits
    ) {
        Course course = new Course(coursecode, subjectname, credits);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }


    // http://localhost:8080/coursequery?coursecode=101&subjectname=Java&credits=3
    @GetMapping("coursequery")
    public ResponseEntity<Course> courseResponseEntity(@RequestParam int coursecode,
                                                       @RequestParam String subjectname,
                                                       @RequestParam int credits){

        Course course = new Course(coursecode,subjectname,credits);
        return ResponseEntity.ok(course);
    }

    @PostMapping("createcourse")
    public ResponseEntity<Course> createCourse(@RequestBody Course course){

        System.out.println(course.getCoursecode());
        System.out.println(course.getSubjectname());
        System.out.println(course.getCredits());
        return ResponseEntity.ok(course);
    }

    @PutMapping("{coursecode}/update")
    public ResponseEntity updateCourse(@PathVariable("coursecode") int coursecode,
                                        @RequestBody Course course){
        return ResponseEntity.accepted().body(course);
//      return ResponseEntity.badRequest().body(("It doesn't have logic"));
    }

    @DeleteMapping("{coursecode}/delete")
    public ResponseEntity deleteCourse(@PathVariable("coursecode") int coursecode){
        return ResponseEntity.ok(course);
    }
}
