package com.firstproj.Courseinfo.cli;

import com.firstproj.Courseinfo.cli.Service.AllCourses;
import com.firstproj.Courseinfo.cli.Service.CourseRetrievalService;
import com.firstproj.Courseinfo.cli.Service.CourseStorageService;
import com.firstproj.courseinfo.Repositry.CourseRepositry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static java.util.function.Predicate.not;

public class CourseRetriever {
    private static final Logger LOG = LoggerFactory.getLogger(CourseRetriever.class);
    public static void main(String[] args) {
        LOG.info("Let's start the CourseRetriever");
        if(args.length == 0){
            LOG.warn("Please enter the course author name as first argument...!");
            return;
        }
           try {
              retrieveCourses(args[0]);
            /*   AllCourses courses = new
                       AllCourses("id", "title", "0:54:67","https://url", false);
               LOG.info("Courses {}", courses);*/ //tempcode to understand Records
           } catch(Exception ex){
               LOG.error("Unexpected Error..!", ex); //log method automatically get the stack trace
              // ex.printStackTrace(); //exception handling and printing the Stack trace
           }

    }//end main

    private static void retrieveCourses(String authorID) {
        LOG.info("Retrieving Courses for Author : '{}' ", authorID);
        CourseRetrievalService courseRetrievalService = new CourseRetrievalService();

        CourseRepositry courseRepositry = CourseRepositry.openCourseReopsitry("./courses.db");
        CourseStorageService courseStorageService = new CourseStorageService(courseRepositry);

       // List<AllCourses> CourseToStore = courseRetrievalService.getCourses(authorID); "Filtering the Courses"
        List<AllCourses> CourseToStore = courseRetrievalService.getCourses(authorID)
                        .stream()
                       // .filter( AllCourses -> !AllCourses.isRetired()) //oneway
                .filter(not(AllCourses::isRetired))
                        .toList();
        LOG.info("Retrieved the following {} course : {}", CourseToStore.size(),CourseToStore);
        courseStorageService.storePluralsightCourses(CourseToStore);
        LOG.info("Courses successfully stored...");
    }//end retrieveCourses

}//end class
