package com.firstproj.courseinfo.Repositry;

import com.firstproj.courseinfo.domain.Course;

import java.util.List;

public interface CourseRepositry {
    void saveCourse(Course course);

    List<Course> getAllCourses();
static CourseRepositry openCourseReopsitry(String databaseFile){
    return new CourseJDBCRepo(databaseFile);
}
}