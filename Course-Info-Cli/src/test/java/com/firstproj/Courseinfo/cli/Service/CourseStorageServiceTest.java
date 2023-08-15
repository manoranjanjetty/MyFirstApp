package com.firstproj.Courseinfo.cli.Service;

import com.firstproj.courseinfo.Repositry.CourseRepositry;
import com.firstproj.courseinfo.domain.Course;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CourseStorageServiceTest {
@Test
 /*   void storePluralsightCourses(){
    CourseRepositry repositry = new InmemoryCourserepositry();
    CourseStorageService storageService = new CourseStorageService(repositry);

    AllCourses allcourses = new AllCourses("1", "Title 1", "01:40:48.000s","/url-1", false);
    CourseStorageService.storePluralsightCourses(List.of(allcourses));
}*/
void storePluralsightCourses() {
    CourseRepositry repository = new InmemoryCourserepositry();
    CourseStorageService courseStorageService = new CourseStorageService(repository);

    AllCourses ps1 = new AllCourses("1", "Title 1",
            "01:40:00.123", "/url-1", false);
    courseStorageService.storePluralsightCourses(List.of(ps1));

    Course expected = new Course("1", "Title 1", 100, "https://app.pluralsight.com/url-1");
   // Optional.empty();
    assertEquals(List.of(expected), repository.getAllCourses());
}

static class InmemoryCourserepositry implements CourseRepositry{
private final List<Course> courses = new ArrayList<>();
    @Override
    public void saveCourse(Course course) {
    courses.add(course);
    }

    @Override
    public List<Course> getAllCourses() {
        return courses;
    }
}
}