package com.course.jdbchrproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data @AllArgsConstructor @NoArgsConstructor
@ToString
public class Employee {
    private Long id;
    private String name;
    private double salary;
    private String job_title;

}
