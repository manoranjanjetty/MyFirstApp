package com.firstproj.courseinfo.Repositry;

import com.firstproj.courseinfo.domain.Course;
import org.h2.jdbcx.JdbcDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

class CourseJDBCRepo implements CourseRepositry {
    private static final String H2_DB_URL =
            "jdbc:h2:file:%s;AUTO_SERVER=TRUE;INIT=RUNSCRIPT FROM './db_init.sql'";

    private static final String INSERT_COURSE = """
            MERGE INTO course(id, name, length, url)
             VALUES (?, ?, ?, ?);
create table course(    id     int,    name   int,    length int,    url    int);            """;
    private static DataSource datasource;

    public CourseJDBCRepo(String databaseFile) {
        JdbcDataSource jdbcDataSource = new JdbcDataSource();
        jdbcDataSource.setURL(H2_DB_URL.formatted(databaseFile));
        this.datasource = jdbcDataSource;
    }

    @Override
    public void saveCourse(Course course) {
        try(Connection connection = datasource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(INSERT_COURSE);
            statement.setString(1, course.id());
            statement.setString(2, course.name());
            statement.setLong(3, course.length());
            statement.setString(4, course.url());
            statement.execute();
        } catch (SQLException ex){
            throw new RepostitryExceptiom("Failed to save...",ex);
        }
    }

    @Override
    public List<Course> getAllCourses() {
        try (Connection connection = datasource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM COURSES");

            List<Course> courses = new ArrayList<>();
            while (resultSet.next()) {
                Course course = new Course(resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getLong(3),
                        resultSet.getString(4));
             //           Optional.ofNullable(resultSet.getString(5)));
                courses.add(course);
            }
            return Collections.unmodifiableList(courses);
        } catch (SQLException ex) {
            throw new RepostitryExceptiom("Failed to retrieve courses", ex);
        }
    }
}
