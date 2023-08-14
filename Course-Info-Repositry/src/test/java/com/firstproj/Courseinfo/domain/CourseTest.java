package com.firstproj.Courseinfo.domain;

import com.firstproj.courseinfo.domain.Course;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {

    @Test
    void rejectNullComponents() {
        assertThrows(IllegalArgumentException.class, () ->
                new Course(null, null, 1, null));
    }

   @Test
    void rejectBlankNotes() {
        assertThrows(IllegalArgumentException.class, () ->
                new Course("1", "title", 1, "url"));
    }
}