package com.example.lab1gui;

public class Course {
    private String courseTitle;
    private String courseDescription;
    private String recordId;

    public Course(String courseTitle, String courseDescription,  String recordId){
        this.courseTitle = courseTitle;
        this.courseDescription = courseDescription;
        this.recordId = recordId;
    }

    public void setCourseTitle(String courseTitle){
        this.courseTitle = courseTitle;
    }
    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }
}
