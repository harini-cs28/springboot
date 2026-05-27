package com.eduhub.eduhub_backend.component;

import org.springframework.stereotype.Component;

@Component
public class Course {

    private String coursecode;
    private String subjectname;
    private int credits;

    public Course() {
    }

    public Course(String coursecode, String subjectname, int credits) {
        this.coursecode = coursecode;
        this.subjectname = subjectname;
        this.credits = credits;
    }

    public String getCoursecode() {
        return coursecode;
    }

    public void setCoursecode(String coursecode) {
        this.coursecode = coursecode;
    }

    public String getSubjectname() {
        return subjectname;
    }

    public void setSubjectname(String subjectname) {
        this.subjectname = subjectname;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }
}