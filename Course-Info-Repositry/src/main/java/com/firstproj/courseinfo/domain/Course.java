package com.firstproj.courseinfo.domain;

import java.util.Optional;

public record Course(String id, String name, long length, String url) {
    public Course{
        filled(id);
        filled(name);
        filled(url);
    }



    private static void filled(String S){
        if(S == null || S.isBlank()){
            throw new IllegalArgumentException("No Values Available...!");
        }
    }
}
