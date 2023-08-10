package com.firstproj.Courseinfo.cli.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CourseRetrievalService {
    private static final String URL_NAME = "https://app.pluralsight.com/profile/data/author/%s/all-content";
   // private static HttpClient CLIENT = HttpClient.newHttpClient(); //Handling this in better way instead of static factory method
   private static HttpClient CLIENT = HttpClient
           .newBuilder()
           .followRedirects(HttpClient.Redirect.ALWAYS)
           .build();
    //Http client Request
    public String getCourses(String authorID){
        HttpRequest request = HttpRequest
                .newBuilder(URI.create(URL_NAME.formatted(authorID)))
                .GET()
                .build();
       try {
           HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
           // instead of response body "response.body();"
           return switch (response.statusCode()){
               case 200 -> response.body();
               case 404 -> "";
               default -> throw new RuntimeException("Pluralsight API call failed...!" + response.statusCode());
           };
       }catch (IOException | InterruptedException ex){
            throw new RuntimeException("Pluralsight API cannot be accessible and Called", ex);
       }
    }
}
