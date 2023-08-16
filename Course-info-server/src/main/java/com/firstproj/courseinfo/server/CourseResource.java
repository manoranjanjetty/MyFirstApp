package com.firstproj.courseinfo.server;

import com.firstproj.courseinfo.Repositry.CourseRepositry;
import com.firstproj.courseinfo.domain.Course;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Collectors;

@Path("/course")
public class CourseResource {
   private static final Logger LOG = LoggerFactory.getLogger(CourseResource.class);
   private final CourseRepositry courseRepositry;

 @Inject
    public CourseResource(CourseRepositry courseRepositry) {
        this.courseRepositry = courseRepositry;
    }

    @GET
    public String getCourses(){
        return courseRepositry
                .getAllCourses()
                .stream()
                .map(Course::toString)
                .collect(Collectors.joining(" ,"));
    }
}
