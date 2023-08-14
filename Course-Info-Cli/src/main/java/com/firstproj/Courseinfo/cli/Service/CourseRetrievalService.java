package com.firstproj.Courseinfo.cli.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class CourseRetrievalService {
    private static final String URL_NAME = "https://app.pluralsight.com/profile/data/author/%s/all-content";
   // private static HttpClient CLIENT = HttpClient.newHttpClient(); //Handling this in better way instead of static factory method
   private static HttpClient CLIENT = HttpClient
           .newBuilder()
           .followRedirects(HttpClient.Redirect.ALWAYS)
           .build();
   private static final ObjectMapper object = new ObjectMapper();
    //Http client Request
    public List<AllCourses> getCourses(String authorID){
        HttpRequest request = HttpRequest
                .newBuilder(URI.create(URL_NAME.formatted(authorID)))
                .GET()
                .build();
       try {
           HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
           // instead of response body "response.body();"
           return switch (response.statusCode()){
              // case 200 -> response.body();
               case 200 -> getAllCourses(response);
               case 404 -> List.of();
               default -> throw new RuntimeException("Pluralsight API call failed...!" + response.statusCode());
           };
       }catch (IOException | InterruptedException ex){
            throw new RuntimeException("Pluralsight API cannot be accessible and Called", ex);
       }
    }

    private static List<AllCourses> getAllCourses(HttpResponse<String> response) throws JsonProcessingException {
        JavaType returnType = object.getTypeFactory()
                .constructCollectionType(List.class, AllCourses.class);
        return object.readValue(response.body(), returnType);
    }
}
