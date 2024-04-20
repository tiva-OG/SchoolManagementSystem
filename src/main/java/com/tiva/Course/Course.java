package com.tiva.Course;

public class Course {

    private String courseCode;
    private String courseTitle;
    private int courseLevel;
    private int creditUnits;

    public Course(String courseCode, String courseTitle, int courseLevel, int creditUnits) {
        this.courseCode = courseCode;
        this.courseTitle = courseTitle;
        this.courseLevel = courseLevel;
        this.creditUnits = creditUnits;
    }

    public Course(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public int getCourseLevel() {
        return courseLevel;
    }

    public int getCreditUnits() {
        return creditUnits;
    }
}
