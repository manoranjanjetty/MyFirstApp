package com.firstproj.Courseinfo.cli;

import com.firstproj.Courseinfo.cli.Service.CourseRetrievalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
           } catch(Exception ex){
               LOG.error("Unexpected Error..!", ex); //log method automatically get the stack trace
              // ex.printStackTrace(); //exception handling and printing the Stack trace
           }

    }//end main

    private static void retrieveCourses(String authorID) {
        LOG.info("Retrieving Courses for Author : '{}' ", authorID);
        CourseRetrievalService courseRetrievalService = new CourseRetrievalService();
        String CourseToStore = courseRetrievalService.getCourses(authorID);
        LOG.info("Retrieved the following course : {}", CourseToStore);
    }//end retrieveCourses

}//end class
