package com.eduhub.eduhub_backend.controller;

import com.eduhub.eduhub_backend.component.CourseService;
import com.eduhub.eduhub_backend.component.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseController {
    @Autowired //dependency - injection
    CourseService courseService;
    /*public CourseController(CourseService courseService){
        this.courseService = courseService;
    } */
    @Autowired
    DemoService demoService;

    @GetMapping("get-course")
    public String getCourse(){
        return courseService.getCourse();
    }

    @GetMapping("get-service")
    public String getService(){
        return demoService.getService();
    }
}
