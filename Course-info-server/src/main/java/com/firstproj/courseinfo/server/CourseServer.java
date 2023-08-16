package com.firstproj.courseinfo.server;

import com.firstproj.courseinfo.Repositry.CourseRepositry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CourseServer {
    private static final Logger LOG = LoggerFactory.getLogger(CourseResource.class);
    public static void main(String... args){
        LOG.info("Starting HTTP server");
        CourseRepositry courseRepositry = CourseRepositry.openCourseReopsitry("./courses.db");
    }
}
