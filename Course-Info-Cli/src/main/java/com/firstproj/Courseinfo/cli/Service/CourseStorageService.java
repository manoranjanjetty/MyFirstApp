package com.firstproj.Courseinfo.cli.Service;

import com.firstproj.courseinfo.Repositry.CourseRepositry;
import com.firstproj.courseinfo.domain.Course;

import java.util.List;
import java.util.Optional;

public class CourseStorageService {
    private static final String PS_BASE_URL = "https://app.pluralsight.com";

    private final CourseRepositry courseRepository;

    public CourseStorageService(CourseRepositry courseRepository) {
        this.courseRepository = courseRepository;
    }

    public void storePluralsightCourses(List<AllCourses> psCourses) {
        for (AllCourses psCourse : psCourses) {
            Course course = new Course(psCourse.id(),
                    psCourse.title(), psCourse.DurationInMinutes(),
                    PS_BASE_URL + psCourse.contentUrl());
            // Optional.empty()
            courseRepository.saveCourse(course);
        }
    }
}
