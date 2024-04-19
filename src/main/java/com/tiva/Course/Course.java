package com.tiva.Course;

public class Course {

    private final String courseCode;
    private final String courseTitle;
    private final int courseLevel;
    private final int creditUnits;

    public Course(String courseCode, String courseTitle, int courseLevel, int creditUnits) {
        this.courseCode = courseCode;
        this.courseTitle = courseTitle;
        this.courseLevel = courseLevel;
        this.creditUnits = creditUnits;
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
