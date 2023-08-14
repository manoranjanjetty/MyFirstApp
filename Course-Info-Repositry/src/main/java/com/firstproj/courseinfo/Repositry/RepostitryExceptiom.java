package com.firstproj.courseinfo.Repositry;

import com.firstproj.courseinfo.domain.Course;

import java.sql.SQLException;

public class RepostitryExceptiom extends RuntimeException {
    public RepostitryExceptiom(String message, SQLException ex) {
        super(message,ex);

    }
}
