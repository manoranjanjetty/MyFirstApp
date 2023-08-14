package com.firstproj.Courseinfo.cli.Service;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
//import org.junit.runners.Parameterized;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AllCoursesTest {

    @ParameterizedTest
    @CsvSource(textBlock = """
            00:05:27, 5
            01:23:45.674543433, 83
            00:00:00.00000, 0
            """)
    void durationInMinutes(String input, long expected) {
        AllCourses courses =
                new AllCourses("id", "test course", input,"url", false);
        assertEquals(expected, courses.DurationInMinutes());
    }
   /* @Test
    void durationInMinutesOverHour(){
        AllCourses courses =
                new AllCourses("id", "test course", "01:05:27","url", false);
        assertEquals(65, courses.DurationInMinutes());
    }
    @Test
    void durationInMinutesZero(){
        AllCourses courses =
                new AllCourses("id", "test course", "00:00:00","url", false);
        assertEquals(0, courses.DurationInMinutes());
    }*/


}