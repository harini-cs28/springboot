package com.eduhub.eduhub_backend.component;

import org.springframework.stereotype.Component;

@Component
public class Course {
    private int coursecode;
    private String subjectname;
    private int credits;

    public Course(){
    }

    public Course(int coursecode,String subjectname, int credits){
        this.coursecode = coursecode;
        this.subjectname = subjectname;
        this.credits = credits;
    }

    public int getCoursecode() {
        return coursecode;
    }

    public String getSubjectname(){
        return subjectname;
    }

    public int getCredits(){
        return credits;
    }

    public void setCoursecode(int coursecode) {
        this.coursecode = coursecode;
    }

    public void setSubjectname(String subjectname) {
        this.subjectname = subjectname;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }
}
