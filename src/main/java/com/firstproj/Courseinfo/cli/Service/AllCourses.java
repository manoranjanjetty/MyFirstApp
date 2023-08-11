package com.firstproj.Courseinfo.cli.Service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.Duration;
import java.time.LocalTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AllCourses(String id, String title, String duration, String contentUrl, boolean isRetired) {
// duration = "00:05:27"
    public long DurationInMinutes(){
        return Duration.between(
            LocalTime.MIN,
                    LocalTime.parse(duration())
        ).toMinutes();


    }
}
